# Create a policy document for Secrets Manager access
cat > secrets-policy.json << EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "secretsmanager:GetSecretValue"
            ],
            "Resource": "arn:aws:secretsmanager:us-east-1:860756134448:secret:*"
        }
    ]
}
EOF

# Create the policy
aws iam create-policy \
    --policy-name ECSSecretsManagerPolicy \
    --policy-document file://secrets-policy.json

# Attach the policy to the role
aws iam attach-role-policy \
    --role-name ecs-task-execution-role \
    --policy-arn arn:aws:iam::860756134448:policy/ECSSecretsManagerPolicy
