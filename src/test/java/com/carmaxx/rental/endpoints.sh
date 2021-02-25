echo "INITIATING CARS ...\n"

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "name": "Lexus IS 350"
      }' \
  http://127.0.0.1:8080/api/v1/car

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "name": "BMW M3"
      }' \
  http://127.0.0.1:8080/api/v1/car


if [ $? -eq 0 ]; then
    echo "\n\n Two cars added successfully! \n\n"
else
    echo "\n\n Unable to add cars ... \n\n"
fi


echo "List of all cars: "

curl http://127.0.0.1:8080/api/v1/car | jq

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "name": "Mercedez Benz AMG GT"
      }' \
  http://127.0.0.1:8080/api/v1/car


if [ $? -ne 0 ]
then
    echo "Failed to add another car ..."
else
    echo "\n\nAdded another car! \n"
fi


echo "\nNew list of all cars: \n\n"

curl http://127.0.0.1:8080/api/v1/car | jq
