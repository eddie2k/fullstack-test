FROM node:12.4.0-alpine as build
RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json /app/package.json
RUN npm install
RUN npm install react-scripts@3.0.1 -g
COPY . /app
RUN npm run build
CMD npm start