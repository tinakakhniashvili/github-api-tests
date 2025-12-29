## How to run

### Web UI (SauceDemo)
1) Start Selenium:
- Apple Silicon:
  docker run -d --name selenium-chrome -p 4444:4444 --shm-size=2g seleniarm/standalone-chromium:latest
- Intel / amd64:
  docker run -d --name selenium-chrome -p 4444:4444 --shm-size=2g selenium/standalone-chrome:latest

2) Run:
mvn clean test -Dsuite=web

### API (GitHub)
mvn clean test -Dsuite=api
