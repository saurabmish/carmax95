echo "\n#######################"
echo "Initiating car list ..."
echo "#######################\n"

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "available": "Y",
          "name": "IS 350 F",
          "manufacturer": "Lexus",
          "owned times": 0
      }' \
  http://127.0.0.1:8080/api/v1/car

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "available": "N",
          "name": "M3 GTR",
          "manufacturer": "BMW",
          "owned times": 0
      }' \
  http://127.0.0.1:8080/api/v1/car

if [ $? -eq 0 ]; then
    echo "\n\n Two cars added successfully! \n\n"
else
    echo "\n\n Unable to add cars ... \n\n"
fi


echo "List of all cars: "

curl http://127.0.0.1:8080/api/v1/car | jq


echo "\n##########################"
echo "Adding car in the list ..."
echo "##########################\n"

curl --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "available": "Y",
          "name": "AMG GT",
          "manufacturer": "Mercedes Benz",
          "owned times": 0
      }' \
  http://127.0.0.1:8080/api/v1/car

if [ $? -ne 0 ]
then
    echo "Failed to add another car ..."
else
    echo "\n\nSuccessfully added another car! \n"
fi


echo "\nNew list of all cars: \n\n"

curl http://127.0.0.1:8080/api/v1/car | jq


echo "\n#################################"
echo "Editing first car in the list ..."
echo "#################################"

uuid1=$(curl --silent http://127.0.0.1:8080/api/v1/car | jq -r '[.[].id] | .[0]')

if [ $? -eq 0 ]; then
    echo "\n\n Found car for update operation! \n"
else
    echo "\n\n Unable to update; Car with ID not found... \n\n"
fi

curl --request PUT \
  --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  --data '
      {
          "available": "Y",
          "name": "Chiron GT",
          "manufacturer": "Bugatti",
          "owned times": 0
      }' \
  http://127.0.0.1:8080/api/v1/car/$uuid1

if [ $? -ne 0 ]
then
    echo "Failed to update existing car ..."
else
    echo "\n\n Car updated successfully! \n"
fi

echo "\nNew list of all cars: \n\n"

curl http://127.0.0.1:8080/api/v1/car | jq


echo "\n###################################"
echo "Deleting second car in the list ..."
echo "###################################"

uuid2=$(curl --silent http://127.0.0.1:8080/api/v1/car | jq -r '[.[].id] | .[1]')

if [ $? -eq 0 ]; then
    echo "\n\n Found car for delete operation! \n"
else
    echo "\n\n Unable to delete; Car with ID not found... \n\n"
fi

curl --request DELETE \
  --verbose --include \
  --header 'Content-Type: application/json' 'Accept: application/json' \
  http://127.0.0.1:8080/api/v1/car/$uuid2

if [ $? -ne 0 ]
then
    echo "Failed to delete existing car ..."
else
    echo "\n\n Car deleted successfully! \n"
fi

echo "\nNew list of all cars: \n\n"

curl http://127.0.0.1:8080/api/v1/car | jq
