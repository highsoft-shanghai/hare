FROM nginx

WORKDIR /usr/src/app/

COPY ./docker/nginx.conf /etc/nginx/conf.d/default.conf

COPY ./dist/spa  /usr/share/nginx/html/

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
