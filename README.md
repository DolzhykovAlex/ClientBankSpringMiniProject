
### Программа без ФРОНТУ. Запити робляться через Postman
### Перелік 

**1.http://localhost:9000/app/customers**
- перелік наявних користувачів

**2.http://localhost:9000/app/customers/one?id=1**
- запит  користувача с заданним id

**3.http://localhost:9000/app/customers/add**

-додавання користвача тіло запита для прикладу,  типу POST
{
"name": "First",
"email": "mail@number1",
"age": 10,
"accounts":[],
"works":[{
"id": 6,
"name": "Work 1",
"address": "Kiev"
}]
}

**4.http://localhost:9000/app/customers/up/user**
- оновлення даних коритстувача за ім'ям та поштою метод PUT,
приклад запитту
- {
  "name": "First",
  "email": "mail@number1",
  "age": 10,
  "accounts":[],
  "works":[{
    "id": 6,
    "name": "Work 1",
    "address": "Kiev"
           }]
  }


**5.http://localhost:9000/app/customers/up/admin?id=4**
- оновлення даних коритстувача за id метод PUT,
приклад запитту {
  "name": "First",
  "email": "mail@number1",
  "age": 10,
  "accounts":[],
  "works":[{
  "id": 6,
  "name": "Work 1",
  "address": "Kiev"
  }]
  }
- 
**6.http://localhost:9000/app/customers/del/entity**
- видалення користувача за ім'ям та поштою метод DELETE

**7.http://localhost:9000/app/customers/del/personal?id=3**
-видалення користувача за id метод DELETE

**8.http://localhost:9000/aсс/add**
-створення нового рахунку, метод POST,
приклад 
{
  "customer" : {
    id": 1,
    "name": "First",
    "email": "mail@number1",
    "age": 10
               },
  "currency": "EUR"
}

**9.http://localhost:9000/acc/all**
- перелік наявних аккаунтів

**10.http://localhost:9000/acc/rich**
-додавання на рахунок метод  PUT,
приклад: генеруеться кожноно разу нове значення номера рахунку {
"numbers":[
"6c98f0f7-9714-4d7e-9e65-70376fee3aa7" 
],
"sum":  123.323
}

**11.http://localhost:9000/acc/poor**
-віднімання з рахунка метод  PUT,
приклад: генеруеться кожноно разу нове значення номера рахунку {
"numbers":[
"6c98f0f7-9714-4d7e-9e65-70376fee3aa7"
],
"sum":  122.323
}

**12.http://localhost:9000/acc/transfer**
-переказ з рахунка на рахунок метод  PUT,
приклад: генеруеться кожноно разу нове значення номера рахунку
{
"numbers":[
         "6c98f0f7-9714-4d7e-9e65-70376fee3aa7",
         "c7264276-5acb-4e30-b8e5-bea6c83cb7e6"
    ],
    "sum": 100.003
}

**13.http://localhost:9000/emp/all**

- перелік наявних місць роботи(Employee)

**14.http://localhost:9000/emp/add**

-створення нового Employee  , метод POST,
приклад
{
"name":"Work 5",
"address":"Kiev"
}

**15.http://localhost:9000/emp/del**

-видалення Employee  , метод DELETE,
приклад

!!! поки можна видалити лише тільки місце у якоЇ
нема працівників
{
"id":10,
"name":"Work 5",
"address":"Kiev"
}

**16.http://localhost:9000/emp/up**
оновлюэ данні Employee


