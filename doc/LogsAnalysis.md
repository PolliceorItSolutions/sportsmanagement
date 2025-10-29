to check the logs, we first need to know the logstream
aws logs describe-log-streams --log-group-name /ecs/sportsmanagement

for each logsteeam, we need to check the logs.

aws logs get-log-events --log-group-name /ecs/sportsmanagement-task --log-stream-name <log-stream-name>
