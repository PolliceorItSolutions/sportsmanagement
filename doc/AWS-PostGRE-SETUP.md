1. **Create a PostgreSQL RDS instance** (I'll provide parameters suitable for development/testing - adjust as needed for production):
   aws rds create-db-instance \
   --db-instance-identifier sports-db \
   --db-instance-class db.t3.micro \
   --engine postgres \
   --master-username sports_user \
   --master-user-password "Polliceor!234" \
   --allocated-storage 20 \
   --engine-version 17.6 \
   --port 5432 \
   --db-name sportsdb \
   --publicly-accessible \
   --region us-east-1

2. **Get the endpoint** (wait a few minutes for the instance to be available):
   aws rds describe-db-instances \
   --db-instance-identifier sports-db \
   --query 'DBInstances[0].Endpoint.Address' \
   --output text
3. **Update your application.properties** with the new RDS endpoint. Replace the existing datasource properties with:

spring.datasource.url=jdbc:postgresql://<your-rds-endpoint>:5432/sportsdb
spring.datasource.username=sports_user
spring.datasource.password=Polliceor!234

4. **Configure Security Group** to allow access:
# Create a security group for RDS
aws ec2 create-security-group \
--group-name sports-db-sg \
--description "Security group for Sports Management RDS"

# Add inbound rule to allow PostgreSQL access from your IP
aws ec2 authorize-security-group-ingress \
--group-name sports-db-sg \
--protocol tcp \
--port 5432 \
--cidr $(curl -s https://checkip.amazonaws.com)/32



