
### Программа без ФРОНТУ. Запити робляться через Postman
### Перелік 

**1.http://localhost:9000/app/customers/all**
- перелік наявних користувачів

**2.http://localhost:9000/app/customers?page=1&size=3**
- перелік наявних користувачів з пагінацією


**3.http://localhost:9000/app/customers/one?id=1**
- запит  користувача с заданним id

**4.http://localhost:9000/app/customers/add**

-додавання користвача тіло запита для прикладу,  типу POST
{
"name":"DDDD",
"email":"last@example.ecom",
"age":20,
"password":"!@#123",
"phone":"+380504712895"
}

**5.http://localhost:9000/app/customers/up/user**
- оновлення даних коритстувача за ім'ям та поштою метод PUT,
приклад запитту
  {   "name":"BBB",
  "email":"BBB@AA.EE",
  "age":22,
  "password":"password2",
  "phone":"+380501234567"
  }


**6.http://localhost:9000/app/customers/up/admin?id=4**
- оновлення даних коритстувача за id метод PUT,
приклад запитту {
  "name":"BB33",
  "email":"BBB@AA.EE",
  "age":22,
  "password":"password2",
  "phone":"+380501234567"}
- 
**7.http://localhost:9000/app/customers/del/entity**
- видалення користувача за ім'ям та поштою метод DELETE

**8.http://localhost:9000/app/customers/del/personal?id=3**
-видалення користувача за id метод DELETE

**9.http://localhost:9000/aсс/add**
-створення нового рахунку, метод POST,
приклад {

    "customer" : {
        "id": 6,
        "name": "CCC3",
        "email": "CCC@AA.EE",
        "age": 30
    },
    "currency": "GBP"
}

**10.http://localhost:9000/acc/all**
- перелік наявних аккаунтів

**11.http://localhost:9000/acc/rich**
-додавання на рахунок метод  PUT,
приклад:{
"numbers":[
"fbb509bc-1608-4806-98ba-d53469463ad4"
],
"sum":  123.323
}

**12.http://localhost:9000/acc/poor**
-віднімання з рахунка метод  PUT,
приклад: генеруеться кожноно разу нове значення номера рахунку {
"numbers":[
"fbb509bc-1608-4806-98ba-d53469463ad4"
],
"sum":100
}

**13.http://localhost:9000/acc/transfer**
-переказ з рахунка на рахунок метод  PUT,
приклад: генеруеться кожноно разу нове значення номера рахунку{
"numbers":[
         "fbb509bc-1608-4806-98ba-d53469463ad4",
         "3dc027c5-a282-4ca0-bd85-e09cf978ac66"
    ],
    "sum": 10.003
}

**14.http://localhost:9000/emp/all**

- перелік наявних місць роботи(Employee)

**15.http://localhost:9000/emp/add**

-створення нового Employee  , метод POST,
приклад
{
"name":"Work 11",
"address":"Kiev"
}

**16.http://localhost:9000/emp/del**

-видалення Employee  , метод DELETE,
приклад

!!! поки можна видалити лише тільки місце у якоЇ
нема працівників
{
"id":10,
"name":"Work 5",
"address":"Kiev"
}

**17.http://localhost:9000/emp/up**
оновлюэ данні Employee


