#! /bin/bash
git reset --hard origin/master
git clean -f
git pull
yarn build
pm2 start npm --name 'lushx' -- start
