# eshop

Advanced Programming 2023/2024 Tutorial. Muhammad Nabil Mu'afa - 2206024972

## Tutorial 1

### Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Menurut saya, salah satu *coding standard* yang sudah saya terapkan adalah abstraksi dalam arsitektur MVC. Segala logika program dan basis data saya "sembunyikan" di repository, segala logika tampilan web saya tuliskan di controller, sementara service digunakan sebagai "pengait" antara controller dan repository. Selain itu, program yang saya tuliskan seharusnya sudah menerapkan beberapa prinsip _clean code_, setidaknya beberapa yang krusial. Salah satu implementasi _clean code_ yang cukup terlihat adalah penggunaan UUID secara random dalam mendefinisikan ID setiap objek `Product`. Hal ini membuat website aman dari _vulnerability_ IDOR (_Insecure Direct Object Reference_). Apabila ID setiap objek  `Product` menggunakan sistem bilangan bulat yang _incremental_ (0, 1, 2, ...), maka pengguna lain dapat dengan mudah mengakses _endpoint_ `delete/{id}` atau `edit/{id}` menggunakan ID objek lain (bahkan bisa secara acak memasukkan suatu bilangan bulat sebagai ID) lalu menghapus atau mengubah atribut objek tersebut meskipun objeknya bukan buatan mereka. Akan tetapi, dengan menggunakan UUID yang acak sebagai ID produk, seorang pengguna tidak bisa asal memasukkan bilangan bulat untuk menghapus atau mengubah atribut objek.

### Reflection 2
> 1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?

> 2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the **number of items in the product list.** You decided to create a new Java class similar to the prior functional test suites with **the same setup procedures and instance variables.** <br><br>What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

1. Setelah membuat unit tests, saya merasa "ada beban yang terangkat" karena kode yang telah saya tulis bisa lebih mudah dipastikan berfungsi atau tidaknya secara otomatis menggunakan unit test. Dalam penulisan unit test, saya rasa tidak perlu ada terlalu banyak test, yang penting test yang telah dituliskan mencakup sebagian besar program yang memerlukan ujicoba. Dengan memastikan hal tersebut, code coverage dari tests dapat sekaligus ditingkatkan hingga dapat dipastikan unit test yang telah dibuat sudah cukup untuk memverifikasi jalannya program yang dibuat. Saya yakin pada kode saya masih terdapat celah-celah tertentu yang belum saya cover pada unit test. Akan tetapi, saya sudah membuat unit test untuk beberapa kasus-kasus kecil yang krusial. Meski begitu, karena belum terdapat code coverage, kemungkinan masih ada bug atau error yang belum saya deteksi.

2. Menurut saya, pembuatan Java class yang baru akan kurang efisien dan mengurangi kualitas testing. Mengingat prosedur setup dan instance variables yang sama, kita bisa memanfaatkan CreateProductFunctionalTest yang telah ada. Di dalam class itu, kita dapat menambahkan test case yang membuat beberapa objek baru. Kemudian, dapat dilakukan verifikasi atau test dengan menyamakan jumlah dari objek yang ter-display pada website dengan objek yang terdapat pada repositori. Test case ini masih relevan dan sesuai dengan test case lain yang terdapat dalam CreateProductFunctionalTest, sehingga masih "sah-sah saja" untuk dituliskan di dalam CreateProductFunctionalTest.

## Tutorial 2

### Reflection

> You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.
> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

1. Isu-isu kualitas pada kode saya yang paling banyak adalah penggunaan modifier `public` pada test. Seharusnya, class test atau method test dari JUnit5 memiliki visibility `default`, sehingga strategi saya untuk memperbaiki isunya adalah dengan menghapus modifier `public` pada setiap class test. Selain itu, ada juga isu dimana tidak ada testcase sama sekali untuk `ProductControllerTest`, tetapi itu karena memang test tersebut belum selesai saya kerjakan, sehingga strateginya adalah selanjutnya saya tambahkan testcase pada class test tersebut. Ada pula isu dimana tidak ada assertion pada `EshopApplicationTests`, tetapi saya belum terpikir bagaimana cara untuk menyelesaikan isu tersebut, karena testcase pada EshopApplicationTests murni dibuat agar "menyenangkan" JaCoCo. Terakhir, ada isu pada class `ProductServiceImpl` dan `ProductController`, dimana penggunaan field injection (penggunaan `@Autowired` pada instansiasi `ProductRepository` dan `ProductService`) bukan merupakan practice yang baik, sehingga saya ubah menjadi menggunakan constructor injection. Secara garis besar menghasilkan hal yang sama, tetapi instansiasinya adalah lewat constructor.

2. Menurut saya sudah, karena terdapat workflow yang secara otomatis menjalankan tests pada kode dan melakukan scanning terhadap kode apabila terdapat push baru. Hal ini membuat saya dapat memastikan aplikasi tidak bermasalah apabila terdapat fitur baru yang diintegrasikan ke branch utama, karena automated testing yang telah dituliskan dapat memeriksa apabila terdapat kerusakan setelah fitur tersebut diintegrasikan. Selain itu, deployment yang saya integrasikan dengan proyek, Koyeb, juga sudah mengimplementasikan continuous deployment. Hal ini menimbang bahwa Koyeb akan selalu melakukan deploy ulang apabila terdapat push baru pada branch utama. Dengan ini, dapat dipastikan versi terdeploy dari proyek adalah versi terbaru. Oleh karena itu, saya rasa implementasi kode saya sudah sesuai dengan definisi Continuous Integration dan Continuous Deployment.

## Tutorial 3

### Reflection

> 1. Explain what principles you apply to your project!

- **Single Responsibility Principle**: Diimplementasikan dengan memisahkan CarController yang tadinya merupakan subclass dari ProductController menjadi suatu class controller sendiri. Dilakukan karena "tanggung jawab" dari ProductController tidak ada hubungannya dengan CarController dan juga sebaliknya. Setiap Controller tersebut memiliki tanggung jawabnya masing-masing sehingga seharusnya dipisahkan dan tidak saling bergantung/depend, apalagi sebagai subclass. Selain itu, fungsionalitas pembuatan UUID dari CarRepository dipisahkan ke sebuah utility class baru agar fungsionalitas pembuatan UUID lebih fleksibel (apabila ingin diubah, dsb.)
- **Open-Closed Principle**: Diimplementasikan dengan menambahkan method `update()` pada class Car, sehingga apabila nantinya akan ada subclass yang dibuat dari class Car yang memiliki field lain, tidak perlu ada pengecekan tipe subclass untuk melakukan update pada field, subclass bisa cukup meng-override method update() yang disediakan dan update() pada repository akan menyesuaikan dengan tipe subclass.
- **Dependency Inversion Principle**: Diimplementasikan dengan mengubah penggunaan service pada CarController menjadi bergantung pada interface yang ada (CarService), bukan concrete class yang sudah mengimplementasikan interfacenya (CarServiceImpl).

> 2. Explain the advantages of applying SOLID principles to your project with examples. 

Untuk prinsip **SRP**, kelas CarController tidak akan lagi bergantung pada ProductController. Sebagai contoh, apabila dilakukan perubahan pada ProductController, maka CarController tidak akan terkena efeknya. Pada versi sebelum diaplikasikan prinsip SOLID, perubahan pada ProductController bisa saja merusak CarController beserta fungsionalitas-fungsionalitasnya. Untuk prinsip **OCP**, kelas Car kini bisa di-develop lebih lanjut tanpa perlu memodifikasi class lain seperti CarRepository. Sebagai contoh, misalkan saya ingin membuat subclass baru dari Car bernama Truck yang memiliki atribut `wheelQuantity` (jumlah roda pada truk bisa beragam). Tanpa mengimplementasikan prinsip ini, pada CarRepository saya akan perlu menambahkan sebuah if statement untuk memeriksa apakah objek yang sedang di-update merupakan tipe Truck atau bukan. Apabila iya, maka saya perlu mengupdate pula atribut wheelQuantity nya. Dengan menambahkan method update() pada kelas Car, saya bisa meng-override method tersebut pada subclass Truck, dan dengan ini CarRepository tidak perlu dimodifikasi karena akan memanggil method update() dari kelas atau subkelass yang berkaitan. Untuk prinsip **DIP**, spesifiknya pada kelas CarController, penggunaan service telah di-abstraksi dari yang sebelumnya menggunakan concrete class sehingga terdapat lebih banyak fleksibilitas dalam implementasi service. CarController dan service yang digunakan tidak akan terikat dengan detail secara spesifik dari interface yang telah diimplementasi. Sebagai contoh, misalnya terdapat service berbeda jenis yang mengimplementasikan CarService, CarController dapat menyesuaikan service yang digunakan pada setiap method atau mapping nya tanpa menyebabkan error berkelanjutan.

> 3. Explain the disadvantages of not applying SOLID principles to your project with examples.

Kerugian utamanya adalah kode akan menjadi lebih sulit untuk di-maintain. Artinya, ketika orang lain perlu melakukan suatu update atau perubahan, perubahan yang dilakukan bisa saja perlu "mengakar" hingga ke implementasi low-level ketika seharusnya perubahan yang ditambahkan hanya perlu dilakukan pada high-level seperti Controller. Bisa saja perubahan yang dilakukan juga merusak jalannya kode karena setiap kelas masih saling berkaitan tanpa diaplikasikannya prinsip SOLID. Selain itu, kode dapat menjadi lebih unreadable karena struktur kode yang berantakan. Akan sulit untuk mencari "hal-apa-ada-dimana" ketika misalnya tidak diaplikasikan SRP, karena semua method atau class tergabung di dalam satu class. Terakhir, akan lebih sulit untuk membuat unit tests karena dependency pada satu class yang cenderung tercampur-campur.