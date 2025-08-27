# Arquitectura Hexagonal - Proyecto Arka

## Descripción
Este proyecto ha sido migrado de una arquitectura en capas tradicional a una **Arquitectura Hexagonal** (también conocida como Ports and Adapters). Esta arquitectura permite un mejor desacoplamiento entre la lógica de negocio y los detalles de implementación.

## Estructura del Proyecto

### 📁 Domain (Dominio)
Contiene la lógica de negocio pura, independiente de cualquier framework o tecnología.

- **`model/`**: Entidades de dominio (inmutables, con métodos de negocio)
- **`port/in/`**: Puertos de entrada (casos de uso/use cases)
- **`port/out/`**: Puertos de salida (interfaces para persistencia, etc.)

### 📁 Application (Aplicación)
Orquesta la lógica de dominio implementando los casos de uso.

- **`service/`**: Implementaciones de los casos de uso

### 📁 Infrastructure (Infraestructura)
Contiene los adaptadores que implementan los puertos definidos en el dominio.

- **`adapter/in/web/`**: Controladores REST, DTOs de request/response
- **`adapter/out/persistence/`**: Implementaciones JPA, entidades, repositorios
- **`config/`**: Configuración de Spring y beans

## Ventajas de la Arquitectura Hexagonal

1. **Desacoplamiento**: La lógica de negocio no depende de frameworks externos
2. **Testabilidad**: Fácil testing unitario del dominio sin dependencias externas
3. **Flexibilidad**: Cambiar implementaciones (bases de datos, APIs) sin afectar el dominio
4. **Mantenibilidad**: Código más limpio y organizado
5. **Inversión de dependencias**: El dominio define las interfaces que la infraestructura implementa

## Flujo de Datos

```
HTTP Request → Web Controller → Use Case → Domain Service → Repository Port → JPA Adapter → Database
              ↑                                           ↓
           Web Adapter                                Domain Model
```

## Ejemplo de Uso: Módulo User

### 1. Domain Layer
- **`User`**: Entidad de dominio inmutable con métodos de negocio
- **`CreateUserUseCase`**: Puerto de entrada para crear usuarios
- **`UserRepository`**: Puerto de salida para persistencia

### 2. Application Layer
- **`UserService`**: Implementa todos los casos de uso de User

### 3. Infrastructure Layer
- **`UserController`**: Adaptador web que expone endpoints REST
- **`UserPersistenceAdapter`**: Adaptador que implementa UserRepository usando JPA
- **`UserJpaEntity`**: Entidad JPA para persistencia

## Comandos Útiles

### Compilar el proyecto
```bash
./mvnw clean compile
```

### Ejecutar tests
```bash
./mvnw test
```

### Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```

## Migración Realizada

### Antes (Arquitectura en Capas)
- Controladores acoplados a servicios
- Servicios acoplados a repositorios JPA
- Entidades JPA mezcladas con lógica de negocio
- Dependencias directas entre capas

### Después (Arquitectura Hexagonal)
- Dominio independiente de frameworks
- Puertos definen contratos claros
- Adaptadores implementan los puertos
- Inversión de dependencias aplicada

## Próximos Pasos

1. Migrar los demás módulos (Product, Order, etc.) siguiendo el mismo patrón
2. Implementar tests unitarios para el dominio
3. Añadir tests de integración para los adaptadores
4. Considerar implementar Event Sourcing para algunos agregados

## Contacto

Para dudas sobre la arquitectura, consultar con el equipo de desarrollo.
