# Remove the existing 8080 rule
aws ec2 revoke-security-group-ingress \
    --group-id sg-08f239ee98a24bb99 \
    --protocol tcp \
    --port 8080 \
    --cidr 0.0.0.0/0

# Add the correct port 80 rule
aws ec2 authorize-security-group-ingress \
    --group-id sg-08f239ee98a24bb99 \
    --protocol tcp \
    --port 80 \
    --cidr 0.0.0.0/0
