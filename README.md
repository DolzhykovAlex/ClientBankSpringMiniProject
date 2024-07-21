
### Программа без ФРОНТУ. Запити робляться через Postman
### Перелік 

**1.http://localhost:9000/app/customers**
- перелік наявних користувачів

**2.http://localhost:9000/app/customers/one?id=1**
- запит  користувача с заданним id

**3.http://localhost:9000/app/customers/add**

-додавання користвача тіла запита для прикладу,  типу POST
{
"name":"Test1",
"email":"Test@email",
"age": 1000
}

**4.http://localhost:9000/app/customers/up/user**
- оновлення даних коритстувача за ім'ям та поштою метод PUT,
приклад запитту {
  "name":"Test1",
  "email":"Test@email",
  "age":0
  }


**5.http://localhost:9000/app/customers/up/admin?id=4**
- оновлення даних коритстувача за id метод PUT,
приклад запитту {
  "name":"Test1",
  "email":"Test@email",
  "age":0
  }
- 
**6.http://localhost:9000/app/customers/del/entity**
- видалення користувача за ім'ям та поштою метод DELETE

**7.http://localhost:9000/app/customers/del/personal?id=3**
-видалення користувача за id метод DELETE

**8.http://localhost:9000/app/customers/account**
-додавання рахунку користувачу за id, метод PUT,
приклад {
    "customer" : {
        "id": 2,
        "name": "Jon",
        "email": "B@111.com",
        "age": 22
    },
    "currency": "USD"
}

**9.http://localhost:9000/acc/all**
-перелік рахунків, поки немає бази данних зберігається окремо

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


