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

def lambda_handler(event, context):
    
    dynamodb = boto3.resource('dynamodb')
    resource = event["path"]
    http_method = event["httpMethod"]
    
    if http_method == 'GET':
        if resource =="/users":
            return get_all_users(dynamodb)
            
        elif resource == "/users/{id}":
            path_parameter = get_path_parameter(event, "id")
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:
                return get_user(dynamodb, path_parameter)
        
        elif resource == "/users/{id}/friends":
            path_parameter = get_path_parameter(event, "id")
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:    
                return get_friends_of_user(dynamodb, path_parameter)
                
        elif resource == "/users/{id}/friends/books":
            path_parameter = get_path_parameter(event, "id")
            if path_parameter is Error:
                return response(path_parameter.message, 400)
            else:    
                return get_books_of_friends(dynamodb, path_parameter)
                
    elif http_method == 'POST':
        body = json.loads(event["body"])
        print(body.keys())
        if resource == "/users/{id}":
            parameter = get_path_parameter(event, "id")
            if parameter is Error:
                return response(parameter.message, 400)
            else: 
                if "recommendation_id" in body.keys():
                    recommendation_id = body["recommendation_id"]
                    result = post_list_value_for_user(dynamodb, parameter, "recommendations", recommendation_id)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
                
                elif "book_id" in body.keys():
                    book_id = body["book_id"]
                    result = post_list_value_for_user(dynamodb, parameter, "reading_list", book_id)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
            
                elif "friend_id" in body.keys():
                    friend_id = body["friend_id"]
                    result = post_list_value_for_user(dynamodb, parameter, "users_following", friend_id)
                    if result is Error:
                        return response(result.message, 500)
                    else:
                        return response("OK", 200)
            
    return {
        "statusCode": 400,
        "body": "Bad Request"
    } 
    
    
# API endpoints
def get_books_of_friends(dynamodb, id):
    # TODO
    pass

def get_all_users(dynamodb):
    items = get_all_items(dynamodb, 'users')
    json_string = format_dynamo_items_to_json(items, 'users')
    return response(json_string, 200) 
    
def get_user(dynamodb, id):
    item = get_item(dynamodb, 'users', {"id":id})
    json_string = json.dumps(item, cls=DecimalEncoder)
    return response(json_string, 200)

def post_list_value_for_user(dynamodb, user_id, list_name, value):
    table = dynamodb.Table('users')
    result = table.update_item(
        Key = {
            'id': user_id
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
    
def get_friends_of_user(dynamodb, id):
    user = get_item(dynamodb, 'users', {"id":id})
    friend_ids = list(user['users_following'])
    friends =[]
    for id in friend_ids:
        friends.append(get_item(dynamodb, 'users', {"id": id}))
    
    json_string = format_dynamo_items_to_json(friends, 'friends')
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
