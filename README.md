# shopshare
a web application for Chinese students in UK to share the shop order and enjoy the discount of official sites

## Deployment
#### Prerequisites-Linux(open port 3306, 8080, 6397, 80), Docker
```Bash
# download the images of MySQL and run it in the backend
docker pull mysql:5.7
docker volume create mysql_data 
docker run --name mysql -p 3306:3306 -v mysql_data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7

# enter mysql
docker exec -it mysql bash
mysql -uroot -p123456 

# granting access rights
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

```Bash
# download the redis and run it in the backend
docker pull redis:5.0.7
# Set data volume mount point
mkdir -p /home/redis/redis/conf
mkdir -p /home/redis/redis/data

cd /home/redis/redis/conf
wget https://raw.githubusercontent.com/antirez/redis/5.0/redis.conf
# change the config file so it could be connected remotely
note bind 127.0.0.1
protected-mode yes  -> protected-mode no
# run
docker run --restart=always  -d --privileged=true -p 6379:6379 -v /home/redis/conf/redis.conf:/etc/redis/redis.conf -v /home/redis/data:/data --name redis redis:5.0.7 redis-server /etc/redis/redis.conf --appendonly yes
```

```bash
# create the jar file of SpringBoot project and create its image and run
# create the Dokcerfile in the directory of shopshare.jar file
FROM java:8
VOLUME /tmp
ADD shopshare.jar /shopshare.jar
EXPOSE 8080
RUN sh -c 'touch /shopshare.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /shopshare.jar" ]

# save the Dockerfile and run the command to build the image -t with own favorable name
docker build -t shopshare .
# run the image
docker run -it --net=host --name shopshare -p 80:8080 -d shopshare
```

Finally, you could get access the project by `http://server_ip` or `http://server_ip:80` on any browser.

The IP is not intuitive and you could buy a DNS service to map your domain to this address.

