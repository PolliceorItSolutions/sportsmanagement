aws secretsmanager update-secret \
    --secret-id "/sports/db-credentials" \
    --secret-string "{\"username\":\"sports_user\",\"password\":\"Polliceor!234\",\"openapikey\":\"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\",\"port\":\"5432\",\"dbname\":\"sportsdb\",\"endpoint\":\"sports-db.ci14846qodgk.us-east-1.rds.amazonaws.com\"}" \
    --region us-east-1
