# 📚 Library Management RESTful API

Bu proje, Spring Boot kullanılarak geliştirilen bir Kütüphane Yönetim Sistemi REST API'sidir. CRUD işlemleri yapılabilir, kitap ödünç alma senaryoları, stok kontrolü ve veri doğrulama gibi işlevler desteklenmektedir.

## 🛠 Kullanılan Teknolojiler

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- ModelMapper
- PostgreSQL
- Bean Validation (JSR-380)
- RESTful Web Services

---

## 🗃️ Katmanlı Mimari

- `Entity`: Veritabanı tabloları
- `DTO`: İstek/yanıt veri modelleri
- `Repository`: JPA veritabanı işlemleri
- `Service`: İş mantığı
- `Controller`: API uç noktaları
- `Exception`: Global hata yönetimi

---

## 🔗 API Endpoint Dökümantasyonu

| HTTP Yöntemi | Endpoint | Açıklama |
|--------------|----------|----------|
| **GET** | `/api/books` | Tüm kitapları listeler |
| **GET** | `/api/books/{id}` | Belirli bir kitabı ID ile getirir |
| **POST** | `/api/books` | Yeni kitap oluşturur |
| **PUT** | `/api/books/{id}` | Kitap bilgilerini günceller |
| **DELETE** | `/api/books/{id}` | Kitabı siler |

| **GET** | `/api/authors` | Tüm yazarları listeler |
| **GET** | `/api/authors/{id}` | Belirli bir yazarı getirir |
| **POST** | `/api/authors` | Yeni yazar oluşturur |
| **PUT** | `/api/authors/{id}` | Yazarı günceller |
| **DELETE** | `/api/authors/{id}` | Yazarı siler |

| **GET** | `/api/categories` | Tüm kategorileri listeler |
| **GET** | `/api/categories/{id}` | Belirli kategoriyi getirir |
| **POST** | `/api/categories` | Yeni kategori oluşturur |
| **PUT** | `/api/categories/{id}` | Kategoriyi günceller |
| **DELETE** | `/api/categories/{id}` | Kategoriyi siler (bağlı kitap varsa engeller) |

| **GET** | `/api/publishers` | Tüm yayınevlerini listeler |
| **GET** | `/api/publishers/{id}` | Belirli yayınevini getirir |
| **POST** | `/api/publishers` | Yeni yayınevi oluşturur |
| **PUT** | `/api/publishers/{id}` | Yayınevini günceller |
| **DELETE** | `/api/publishers/{id}` | Yayınevini siler |

| **GET** | `/api/borrowings` | Tüm ödünç alma kayıtlarını listeler |
| **POST** | `/api/borrowings` | Kitap ödünç alma (stok kontrolü yapılır) |
| **PUT** | `/api/borrowings/return/{id}?returnDate=yyyy-MM-dd` | Kitap teslim tarihini günceller |

---

## 🧪 Validasyonlar

- DTO seviyesinde `@NotNull`, `@NotBlank`, `@Email`, `@Min` gibi anotasyonlarla veri doğrulaması yapılır.
- Hatalı istekler `400 Bad Request` şeklinde detaylı JSON ile döner.

---

## ⚠️ İş Kuralları

- Kitap stokta yoksa ödünç alma işlemi engellenir.
- Silinmek istenen kategoriye bağlı kitap varsa silinmez.
- Kitap teslim edildiğinde stok sayısı otomatik artar.

---

## 🧰 Global Exception Handling

Tüm `NotFoundException`, `ValidationException` ve diğer hatalar `@RestControllerAdvice` ile merkezi şekilde ele alınır.

---

## 🐘 Veritabanı Yapılandırması (PostgreSQL)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update

