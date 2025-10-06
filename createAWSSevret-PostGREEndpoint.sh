aws secretsmanager update-secret \
    --secret-id "/sports/db-credentials" \
    --secret-string "{\"username\":\"sports_user\",\"password\":\"Polliceor!234\",\"endpoint\":\"sports-db.ci14846qodgk.us-east-1.rds.amazonaws.com\"}" \
    --region us-east-1


