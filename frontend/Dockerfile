FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 4200

# Usamos ng serve apuntando a host 0.0.0.0 para que Docker pueda exponerlo hacia afuera
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--poll", "2000"]