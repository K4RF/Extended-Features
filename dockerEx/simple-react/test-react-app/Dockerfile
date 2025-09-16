FROM node:22-alpine

WORKDIR /src

COPY package.json .

RUN yarn
# or
# RUN npm install

COPY . .

EXPOSE 3000

CMD [ "yarn", "start" ]
# or
# CMD [ "npm", "start" ]