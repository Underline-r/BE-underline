sudo docker pull infinity4869/underline-app
if [ ! -d $(date '+%y%m%d') ]; then
  mkdir $(date '+%y%m%d')
fi
sudo nohup docker run -p 8080:8080 -v /etc/localtime:/etc/localtime:ro -e TZ=Asia/Seoul --name=underline-app infinity4869/underline-app > $(date '+%y%m%d')/log.out 2>&1 &
