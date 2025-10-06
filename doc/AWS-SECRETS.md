AWS Create Sercet for Postgre password

aws secretsmanager create-secret \
--name "/sports/db-credentials" \
--description "Database credentials for Sports Management" \
--secret-string '{"username":"sports_user","password":"Polliceor!234"}'

{
"ARN": "arn:aws:secretsmanager:us-east-1:860756134448:secret:/sports/db-credentials-jeakW5",
"Name": "/sports/db-credentials",
"VersionId": "3292fe77-7f57-469c-8b54-7e0c8374e386"
}


AWS Create Secret for OpenAPI key
aws secretsmanager create-secret \
--name "/sports/api-keys" \
--description "API keys for Sports Management" \
--secret-string '{"openapi-key":"your-api-key-here"}'



{
"ARN": "arn:aws:secretsmanager:us-east-1:860756134448:secret:/sports/api-keys-3UvRS9",
"Name": "/sports/api-keys",
"VersionId": "3bbf02c4-8aea-4098-bdfe-7627d01b214a"
}



aws secretsmanager update-secret \
--secret-id "/sports/db-credentials" \
--secret-string "{\"username\":\"sports_user\",\"password\":\"Polliceor!234\",\"endpoint\":\"sports-db.ci14846qodgk.us-east-1.rds.amazonaws.com\"}" \
--region us-east-1


{
"ARN": "arn:aws:secretsmanager:us-east-1:860756134448:secret:/sports/db-credentials-jeakW5",
"Name": "/sports/db-credentials",
"VersionId": "6104438b-35ab-432e-9b06-3e0afb92edd7"
}

aws secretsmanager get-secret-value --secret-id /sports/db-credentials --region us-east-1


