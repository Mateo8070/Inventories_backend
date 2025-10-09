# Hardware Manager API Documentation

Access key
If you lose or forget your secret access key, you cannot retrieve it. Instead, create a new access key and make the old key inactive.



This document provides a detailed description of the Hardware Manager API.

## Base URL

The base URL for all API endpoints is `/`.

---

## Models

### Category

Represents a category of hardware.

| Field       | Type          | Description                                   |
|-------------|---------------|-----------------------------------------------|
| `id`        | UUID          | The unique identifier for the category.       |
| `name`      | String        | The name of the category.                     |
| `color`     | String        | The color associated with the category.       |
| `isDeleted` | boolean       | A flag indicating if the category is deleted. |
| `updatedAt` | LocalDateTime | The timestamp of the last update.             |
| `hardware`  | Set<Hardware> | The set of hardware items in this category.   |

### Hardware

Represents a piece of hardware.

| Field                | Type          | Description                                     |
|----------------------|---------------|-------------------------------------------------|
| `id`                 | UUID          | The unique identifier for the hardware.         |
| `description`        | String        | A description of the hardware.                  |
| `quantity`           | String        | The quantity of the hardware in stock.          |
| `wholesalePrice`     | double        | The wholesale price of the hardware.            |
| `retailPrice`        | double        | The retail price of the hardware.               |
| `updatedAt`          | LocalDateTime | The timestamp of the last update.               |
| `isDeleted`          | boolean       | A flag indicating if the hardware is deleted.   |
| `wholesalePriceUnit` | String        | The unit for the wholesale price (e.g., USD).   |
| `retailPriceUnit`    | String        | The unit for the retail price (e.g., USD).      |
| `updatedBy`          | String        | The user who last updated the hardware.         |
| `location`           | String (JSON) | The location of the hardware in JSON format.    |
| `category`           | String        | The category to which the hardware belongs.     |
| `imageUrl`           | String        | The URL of the hardware's image.                |

---

## Category API

The Category API provides endpoints for managing hardware categories.

### Get All Categories

*   **Method:** `GET`
*   **Endpoint:** `/categories`
*   **Description:** Retrieves a list of all categories.
*   **Response Body:** A JSON array of `Category` objects.
*   **Example Response:**
    ```json
    [
        {
            "id": "c0a80121-7ac0-11eb-8dcd-0242ac130003",
            "name": "Laptops",
            "color": "#007bff",
            "isDeleted": false,
            "updatedAt": "2025-10-06T20:30:00"
        },
        {
            "id": "c0a80121-7ac0-11eb-8dcd-0242ac130004",
            "name": "Keyboards",
            "color": "#28a745",
            "isDeleted": false,
            "updatedAt": "2025-10-06T20:35:00"
        }
    ]
    ```

### Get Category by ID

*   **Method:** `GET`
*   **Endpoint:** `/categories/{id}`
*   **Description:** Retrieves a specific category by its ID.
*   **URL Parameters:**
    *   `id` (UUID, required): The ID of the category to retrieve.
*   **Response Body:** A JSON `Category` object.
*   **Example Response:**
    ```json
    {
        "id": "c0a80121-7ac0-11eb-8dcd-0242ac130003",
        "name": "Laptops",
        "color": "#007bff",
        "isDeleted": false,
        "updatedAt": "2025-10-06T20:30:00"
    }
    ```

### Create Category

*   **Method:** `POST`
*   **Endpoint:** `/categories`
*   **Description:** Creates a new category.
*   **Request Body:** A JSON `Category` object. The `id`, `isDeleted`, and `updatedAt` fields are ignored.
*   **Example Request:**
    ```json
    {
        "name": "Mice",
        "color": "#ffc107"
    }
    ```
*   **Response Body:** The created JSON `Category` object.
*   **Example Response:**
    ```json
    {
        "id": "c0a80121-7ac0-11eb-8dcd-0242ac130005",
        "name": "Mice",
        "color": "#ffc107",
        "isDeleted": false,
        "updatedAt": "2025-10-06T20:40:00"
    }
    ```

### Update Category

*   **Method:** `PUT`
*   **Endpoint:** `/categories/{id}`
*   **Description:** Updates an existing category.
*   **URL Parameters:**
    *   `id` (UUID, required): The ID of the category to update.
*   **Request Body:** A JSON `Category` object. The `id` from the URL will be used.
*   **Example Request:**
    ```json
    {
        "name": "Gaming Mice",
        "color": "#dc3545"
    }
    ```
*   **Response Body:** The updated JSON `Category` object.
*   **Example Response:**
    ```json
    {
        "id": "c0a80121-7ac0-11eb-8dcd-0242ac130005",
        "name": "Gaming Mice",
        "color": "#dc3545",
        "isDeleted": false,
        "updatedAt": "2025-10-06T20:45:00"
    }
    ```

### Delete Category

*   **Method:** `DELETE`
*   **Endpoint:** `/categories/{id}`
*   **Description:** Deletes a category by its ID.
*   **URL Parameters:**
    *   `id` (UUID, required): The ID of the category to delete.
*   **Response:** `200 OK` if successful.

---

## Hardware API

The Hardware API provides endpoints for managing hardware items.

### Get All Hardware

*   **Method:** `GET`
*   **Endpoint:** `/hardware`
*   **Description:** Retrieves a list of all hardware items.
*   **Response Body:** A JSON array of `HardwareDto` objects.
*   **Example Response:**
    ```json
    [
        {
            "id": "d0a80121-7ac0-11eb-8dcd-0242ac130006",
            "description": "MacBook Pro 16-inch",
            "quantity": "10 pcs",
            "wholesalePrice": 2000.00,
            "retailPrice": 2399.00,
            "updatedAt": "2025-10-06T21:00:00",
            "isDeleted": false,
            "wholesalePriceUnit": "USD",
            "retailPriceUnit": "USD",
            "updatedBy": "admin",
            "location": "{"warehouse": "A", "shelf": "3"}",
            "category": "Laptops",
            "imageUrl": "https://hardware-images-bucket.s3.eu-north-1.amazonaws.com/hardware/1665072000000_macbook.jpg"
        },
        {
            "id": "d0a80121-7ac0-11eb-8dcd-0242ac130007",
            "description": "Logitech MX Master 3",
            "quantity": "50 units",
            "wholesalePrice": 70.00,
            "retailPrice": 99.00,
            "updatedAt": "2025-10-06T21:05:00",
            "isDeleted": false,
            "wholesalePriceUnit": "USD",
            "retailPriceUnit": "USD",
            "updatedBy": "admin",
            "location": "{"warehouse": "B", "shelf": "12"}",
            "category": "Mice",
            "imageUrl": "https://hardware-images-bucket.s3.eu-north-1.amazonaws.com/hardware/1665072300000_logitech_mouse.jpg"
        }
    ]
    ```

### Get Hardware by ID

*   **Method:** `GET`
*   **Endpoint:** `/hardware/{id}`
*   **Description:** Retrieves a specific hardware item by its ID.
*   **Response Body:** A JSON `HardwareDto` object.
*   **Example Response:**
    ```json
    {
        "id": "d0a80121-7ac0-11eb-8dcd-0242ac130006",
        "description": "MacBook Pro 16-inch",
        "quantity": "10 pcs",
        "wholesalePrice": 2000.00,
        "retailPrice": 2399.00,
        "updatedAt": "2025-10-06T21:00:00",
        "isDeleted": false,
        "wholesalePriceUnit": "USD",
        "retailPriceUnit": "USD",
        "updatedBy": "admin",
        "location": "{"warehouse": "A", "shelf": "3"}",
        "category": "Laptops",
        "imageUrl": "https://hardware-images-bucket.s3.eu-north-1.amazonaws.com/hardware/1665072000000_macbook.jpg"
    }

    ```

### Create Hardware

*   **Method:** `POST`
*   **Endpoint:** `/hardware`
*   **Description:** Creates a new hardware item.
*   **Request Body:** A `multipart/form-data` request with two parts:
    *   `hardware`: A JSON string representing the `HardwareDto` object. The `id`, `isDeleted`, and `updatedAt` fields are ignored.
    *   `file`: The image file to be uploaded.
*   **Example Request:**
    *   `hardware` part (JSON):
        ```json
        {
            "description": "Dell UltraSharp 27-inch Monitor",
            "quantity": "25 units",
            "wholesalePrice": 300.00,
            "retailPrice": 450.00,
            "wholesalePriceUnit": "USD",
            "retailPriceUnit": "USD",
            "updatedBy": "admin",
            "location": "{\"warehouse\": \"C\", \"shelf\": \"7\"}",
            "category": "Monitors"
        }
        ```
    *   `file` part: The binary data of the image file.
*   **Response Body:** The created JSON `HardwareDto` object.
*   **Example Response:**
    ```json
    {
        "id": "d0a80121-7ac0-11eb-8dcd-0242ac130008",
        "description": "Dell UltraSharp 27-inch Monitor",
        "quantity": "25 units",
        "wholesalePrice": 300.00,
        "retailPrice": 450.00,
        "updatedAt": "2025-10-06T21:10:00",
        "isDeleted": false,
        "wholesalePriceUnit": "USD",
        "retailPriceUnit": "USD",
        "updatedBy": "admin",
        "location": "{\"warehouse\": \"C\", \"shelf\": \"7\"}",
        "category": "Monitors",
        "imageUrl": "https://hardware-images-bucket.s3.eu-north-1.amazonaws.com/hardware/1665072600000_dell_monitor.jpg"
    }
    ```

### Update Hardware

*   **Method:** `PUT`
*   **Endpoint:** `/hardware/{id}`
*   **Description:** Updates an existing hardware item.
*   **URL Parameters:**
    *   `id` (UUID, required): The ID of the hardware to update.
*   **Request Body:** A `multipart/form-data` request with two parts:
    *   `hardware`: A JSON string representing the `HardwareDto` object. The `id` from the URL will be used.
    *   `file` (optional): The new image file to be uploaded.
*   **Example Request:**
    *   `hardware` part (JSON):
        ```json
        {
            "quantity": "20 pcs",
            "retailPrice": 440.00
        }
        ```
    *   `file` part: The binary data of the new image file.
*   **Response Body:** The updated JSON `HardwareDto` object.
*   **Example Response:**
    ```json
    {
        "id": "d0a80121-7ac0-11eb-8dcd-0242ac130008",
        "description": "Dell UltraSharp 27-inch Monitor",
        "quantity": "20 pcs",
        "wholesalePrice": 300.00,
        "retailPrice": 440.00,
        "updatedAt": "2025-10-06T21:15:00",
        "isDeleted": false,
        "wholesalePriceUnit": "USD",
        "retailPriceUnit": "USD",
        "updatedBy": "admin",
        "location": "{"warehouse": "C", "shelf": "7"}",
        "category": "Monitors",
        "imageUrl": "https://hardware-images-bucket.s3.eu-north-1.amazonaws.com/hardware/1665072900000_dell_monitor_new.jpg"
    }
    ```

### Delete Hardware

*   **Method:** `DELETE`
*   **Endpoint:** `/hardware/{id}`
*   **Description:** Deletes a hardware item by its ID.
*   **URL Parameters:**
    *   `id` (UUID, required): The ID of the hardware to delete.
*   **Response:** `200 OK` if successful.
