import boto3
import random

BASE_URL = "https://s3-us-west-2.amazonaws.com/{}/{}/{}"
BUCKET_NAME = "bookapp-deployments-mobilehub-1770081831"
FOLDER_NAME = "default-profile-photos"

def lambda_handler(event, context):
    dynamodb = boto3.resource('dynamodb')
    table = dynamodb.Table('users')

    username = event["userName"]
    user = table.get_item(Key={"username": username})
    if 'Item' not in user:
        photo_number = random.randint(1, 16)
        user = {
            "email": event['request']['userAttributes']['email'],
            "reading_list": ["14553840", "17557516", "12394068", "860613", "11822750"],
            "recommendations": ["34066800", "31693108", "124753", "27239161", "10880867"],
            "users_following": [],
            "username": event['userName'],
            "bio": "",
            "image_url": BASE_URL.format(BUCKET_NAME, FOLDER_NAME, "cat{}.png".format(photo_number))
        }
        table.put_item(Item=user)
    return event
