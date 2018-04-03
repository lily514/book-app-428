import boto3 
import json
import decimal
from boto3.dynamodb.conditions import Key, Attr

# Helper class to convert a DynamoDB item to JSON.
class DecimalEncoder(json.JSONEncoder):
    def default(self, o):
        if isinstance(o, decimal.Decimal):
            if o % 1 > 0:
                return float(o)
            else:
                return int(o)
        return super(DecimalEncoder, self).default(o)
    
class Error():
    def __init__(self,message):
        self.message = message
        
# Constants
USER_NAME = "username"
RECOMMENDATIONS = "recommendations"
READING_LIST = "reading_list"
USERS_FOLLOWING = "users_following"
USERS = "users"
BOOKS = "books"
FRIENDS = "friends"
ID = "id"

# Handler
def lambda_handler(event, context):
    
    dynamodb = boto3.resource('dynamodb')
    resource = event["resource"]
    print(resource)
    http_method = event["httpMethod"]
    
    if http_method == 'GET':
        if resource =="/users":
            return get_all_users(dynamodb)
            
        elif resource == "/users/{username}":
            path_parameter = get_path_parameter(event, USER_NAME)
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:
                return get_user(dynamodb, path_parameter)
        
        elif resource == "/users/{username}/friends":
            path_parameter = get_path_parameter(event, USER_NAME)
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:    
                return get_friends_of_user(dynamodb, path_parameter)
                
        elif resource == "/users/{username}/friends/books":
            path_parameter = get_path_parameter(event, USER_NAME)
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:    
                return get_books_of_friends(dynamodb, path_parameter)
                
    elif http_method == 'POST':
        body = json.loads(event["body"])
        print(body.keys())
        if resource == "/users/{username}":
            parameter = get_path_parameter(event, USER_NAME)
            if parameter is Error:
                return response(parameter.message, 400)
            else: 
                if "recommendation_id" in body.keys():
                    
                    recommendation_id = int(body["recommendation_id"])
                    result = post_list_value_for_user(dynamodb, parameter, RECOMMENDATIONS, recommendation_id)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
                
                elif "book_id" in body.keys():
                    book_id = int(body["book_id"])
                    result = post_list_value_for_user(dynamodb, parameter, READING_LIST, book_id)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
            
                elif "friend_username" in body.keys():
                    friend_username = body["friend_username"]
                    result = post_list_value_for_user(dynamodb, parameter, USERS_FOLLOWING, friend_username)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
            
    return {
        "statusCode": 400,
        "body": "Bad Request"
    } 
    
    
# API endpoints
def get_books_of_friends(dynamodb, username):
    user = get_item(dynamodb, USERS, {USER_NAME: username})
    if USERS_FOLLOWING in user.keys():
        friend_usernames = list(user[USERS_FOLLOWING])
        friends =[]
        for friend_username in friend_usernames:
            friends.append(get_item(dynamodb, USERS, {USER_NAME: friend_username}))
    
        books = []
        for friend in friends:
            for book in list(friend["reading_list"]):
                books.append(get_item(dynamodb, BOOKS, {ID: str(book)}))
                
        json_string = format_dynamo_items_to_json(books, BOOKS)
        print(json_string)
        return response(json_string, 200)        
        


def get_all_users(dynamodb):
    items = get_all_items(dynamodb, USERS)
    json_string = format_dynamo_items_to_json(items, USERS)
    return response(json_string, 200) 
    
def get_user(dynamodb, username):
    item = get_item(dynamodb, USERS, {USER_NAME: username})
    json_string = json.dumps(item, cls=DecimalEncoder)
    return response(json_string, 200)

def check_if_user_has_list(dynamodb, username, list_name):
    user = get_item(dynamodb, USERS, {USER_NAME: username})
    
    if list_name not in user.keys():
        table = dynamodb.Table(USERS)

        result = table.update_item(
        Key = {
            USER_NAME: user[USER_NAME]
        },
        UpdateExpression = "set {} = :i".format(list_name),
        ExpressionAttributeValues = {
            ':i': []
        },
        ReturnValues = "UPDATED_NEW"
        )
        if result['ResponseMetadata']['HTTPStatusCode'] == 200 and 'Attributes' in result:
            return True    
        else:
            return Error("Internal error, unable to create list.")
    else:
        print("list found")
        return


def post_list_value_for_user(dynamodb, username, list_name, value):
    
    result = check_if_user_has_list(dynamodb, username, list_name)
    if result is Error:
        return result 
        
    table = dynamodb.Table(USERS)    
    result = table.update_item(
        Key = {
            USER_NAME: username
        },
        UpdateExpression = "SET {} = list_append({}, :i)".format(list_name, list_name),
        ExpressionAttributeValues = {
            ':i': [value]
        },
        ReturnValues = "UPDATED_NEW"
    )
    if result['ResponseMetadata']['HTTPStatusCode'] == 200 and 'Attributes' in result:
        return result['Attributes'][list_name]
    else:
        return Error("Internal error, unable to post value.")
    
def get_friends_of_user(dynamodb, username):
    user = get_item(dynamodb, USERS, {USER_NAME: username})
    if USERS_FOLLOWING in user.keys():
        friend_usernames = list(user[USERS_FOLLOWING])
        friends =[]
        for friend_username in friend_usernames:
            print(friend_username)
            friends.append(get_item(dynamodb, USERS, {USER_NAME: friend_username}))
    
        json_string = format_dynamo_items_to_json(friends, FRIENDS)
        print(json_string)
        return response(json_string, 200)
    
# helper methods
def format_dynamo_items_to_json(items, name):
    json_string = '{"' + name + '": ['
    for item in items:
        json_string += json.dumps(item, cls=DecimalEncoder)
        if item is not items[-1]:
            json_string += ","
    json_string += "]}"
    return json_string
        
def get_path_parameter(event, parameter):
    if parameter in event['pathParameters']:
        return event['pathParameters'][parameter]
    else:
        return Error("No path parameter provided.")
  
def response(body, status_code):
    return {
        "body": body,
        "statusCode": status_code
    }
    
def create_table(dynamodb, table_name, key_schema, attribute_definitions, provisioned_throughput):
    table = dynamodb.create_table(
        TableName= table_name,
        KeySchema= key_schema, 
        AttributeDefinitions= attribute_definitions, 
        ProvisionedThroughput= provisioned_throughput
    )
    
def add_item(dynamodb, table_name, item):
    table = dynamodb.Table(table_name)
    table.put_item(Item=item)
    
    
def get_all_items(dynamodb, table_name):
    table = dynamodb.Table(table_name)
    results = table.scan()
    items = results['Items']
    return items

def get_item(dynamodb, table_name, key):
    table = dynamodb.Table(table_name)
    response = table.get_item(Key=key)
    return(response["Item"])