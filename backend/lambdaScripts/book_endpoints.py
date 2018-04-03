import boto3
from goodreads import client
import json
import decimal
from boto3.dynamodb.conditions import Key, Attr



def get_books(title):
    gc=client.GoodreadsClient("koVgEwCYhJNza9qhqZqiQ","n3eQFT0wlV92KiVD2T0oPgX8k51woJecTyFRk0")
    book_list=gc.search_books(title)
    books=[]
    [books.append({ "title":book['best_book']['title'], "author":book['best_book']['author']['name'], "cover_url":book['best_book']['image_url'],"id":book['best_book']['id']['#text'] }) for book in book_list]
    return books

def get_book(gid):
    gc=client.GoodreadsClient("koVgEwCYhJNza9qhqZqiQ","n3eQFT0wlV92KiVD2T0oPgX8k51woJecTyFRk0")
    g=gc.book(gid)
    return {"author" : g.authors[0].name,"cover_url": g.image_url,"description": g.description, "ISBN": g.isbn, "title":g.title, "id":g.gid}

def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb')
    resource = event["resource"]
    http_method = event["httpMethod"]
    if http_method == 'GET':
        if resource=="/books/{title}":
            title=event['pathParameters']['title']
            return{ "statusCode":200, "body": '{books:'+json.dumps(get_books(title))+'}'}
        if resource=="/book/{id}":
            gid=event['pathParameters']['id']
            item=get_item(dynamodb,'books',{'id':gid})
            if 'Item' in item:
                 return{ "statusCode":200, "body": json.dumps(item['Item'])}
            else:
                book=get_book(gid)
                put_item(dynamodb, 'books',book)
                return{"statusCode":200,"body":json.dumps(book)}

            
        
    
def put_item(dynamodb, table_name, item):
    table = dynamodb.Table(table_name)
    table.put_item(Item=item)
    

def get_item(dynamodb, table_name, key):
    print(key)
    table = dynamodb.Table(table_name)
    response = table.get_item(Key=key)
    return(response)
