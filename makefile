.PHONY: clean gradle

# docker 컨테이너, 이미지, 볼륨 삭제
clean:
	@docker stop $$(docker ps -aq) || true
	@docker system prune -a --volumes -f

# dev profile로 gradle 빌드
gradle:
		./gradlew clean build -Dspring.profiles.active=dev -x test