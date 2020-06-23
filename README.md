# Tasker
Backend (REST API) for Simple Task Management Tool

# How to set up local Dev Environment (For Windows 10)

Prerequisites:

1. Install `docker desktop`

2. Install `docker-compose`

Set Up:

1. Clone repository

2. Copy following json configuration and save as servers.json file in project's root directory.
This is needed for using pgAdmin Postgres client tool.
```
{
          "Servers": {
              "1": {
                  "Name": "Tasker Dev",
                  "Group": "Servers",
                  "Port": 5432,
                  "Username": "tasker",
                  "Host": "tasker_postgres_1",
                  "SSLMode": "prefer",
                  "MaintenanceDB": "tasker"
              }
          }
      }'''

2. Inside local clone run `docker-compose up` command

To use pgAdmin open http://localhost:5433

That is it :)  

