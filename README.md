# Malefiz
HTWG Constance - Software Engineering - Semester 3

![MAIN status](https://github.com/Erik-Hoffmann/Malefiz/actions/workflows/scala.yml/badge.svg)

[![codecov](https://codecov.io/gh/Erik-Hoffmann/Malefiz/branch/main/graph/badge.svg?token=RYPOZEZFLF)](https://codecov.io/gh/Erik-Hoffmann/Malefiz)

Compile with `sbt compile`, run with `sbt run`

Run jacoco with `sbt jacoco` and generate a report with `sbt jacocoReport`

Docker was built via

```sh
sudo docker pull hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
sudo docker build -t malefiz:v1 .
sudo docker run -ti malefiz:v1
```