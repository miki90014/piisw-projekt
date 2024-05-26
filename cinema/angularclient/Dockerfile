FROM node:16 as cinema-client
WORKDIR /app
COPY package*.json ./
RUN apk --no-cache add curl
RUN npm install
COPY . .
RUN npm run build --prod --host 0.0.0.0 --disable-host-check
