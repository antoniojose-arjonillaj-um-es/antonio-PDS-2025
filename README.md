# PRÁCTICAS DE LA ASIGNATURA PROCESO DE DESARROLLO SOFTWARE - UNIVERSIDAD DE MURCIA
## Curso 2024-2025

### Alumno
- Antonio José Arjonilla Jiménez - Grupo 3.3

### Profesor
- Marcial Pamies Berenguer

---
## Explicación de las prácticas
El objetivo de las prácticas consiste en construir una aplicación de aprendizaje para aprender y repasar cualquier tipo de dominio.

En este tipo de aplicaciones, la funcionalidad consiste en que el usuario escoge un curso disponible y la aplicación muestra los
diferentes ejercicios uno a uno.

Dentro de un curso pueden existir diferentes bloques de contenidos con diferentes tipos de ejercicios.
El diseño del sistema debe permitir que se pueda extender con nuevos tipos de preguntas para adaptarlos a otros dominios.

---
## Requisitos mínimos de la aplicación pedidos por la asignatura

- Deben existir al menos 3 tipos de preguntas (o pantallas de aprendizaje) diferentes, pero
además debe ser posible añadir nuevos tipos de preguntas de manera sencilla.

- Cuando un usuario va a realizar un curso, tiene que poder elegir la estrategia de aprendizaje.
  
- Cuando un usuario esté realizando un curso debe poder guardarse el estado actual del curso y
reanudarse en cualquier momento.

- La aplicación debe guardar estadísticas de uso como contar el tiempo de uso, calcular la mejor
racha (ej., número de días), etc.

- Se desea que los usuarios puedan crear cursos y compartirlos con otros usuarios. Es decir, la
aplicación debe permitir instalar nuevos cursos en su biblioteca interna. Un curso estará
definido como un fichero JSON o YAML por lo que no es necesario una interfaz de usuario
para desarrollar los cursos.

- Se deberán idear características adicionales que tenga el sistema (mínimo 1).
---
## Características básicas del sistema implementadas
- El formato escogido para los archivos para cargar cursos es YAML.

- Al registrarse, un usuario debe de introducir obligatoriamente su nombre y teléfono (ambos no pueden coincidir con los de otro usuario), contraseña y opcionalmente una imagen (se carga una por defecto si no tiene ninguna).

- Se han desarrollado 4 tipo de estrategias para realizar los cursos:
  - Defecto : se muestran las preguntas en el orden en el que aparecen en el curso.
  - Aleatorio: se muestran las preguntas en un orden aleatorio.
  - Contrarreloj: se muestran en el orden por defecto pero hay tiempo limitado para contestar cada pregunta. Este se muestra en pantalla. Si no se responde a tiempo, se pasa automáticamente la pregunta, recogiendo la respuesta introducida hasta el momento.
  - Contrarreloj aleatorio: como contrarreloj pero desordenando las preguntas

- Se han creado 3 tipos de preguntas:
  - Preguntas tipo test: dada una pregunta, se muestran varias opciones de respuesta. Pueden escogerse varias respuestas a la vez en caso de respuesta múltiple.
  - Preguntas de traducción: dada una pregunta, se muestra un campo de texto para introducir la respuesta.
  - Pregunta de relleno: hay un campo de texto en medio de la pregunta, para introducir la palabra/frase más adecuada para su completitud. 
---
## Características adicionales del sistema desarrolladas

- El usuario tiene una imagen, la cual puede cambiar al hacer doble click sobre esta.

- El usuario dispone de una serie de tickets (10 como máximo) los cuales puede gastar para realizar cursos.
Estos tickets se calculan al iniciar sesión a partir desde la fecha de último inicio de sesión (10 máximo al día, véase, un ticket cada 2,4 horas).

- El usuario puede compartir un curso que tenga importado en la aplicación desde la propia aplicación, indicando el curso a compartir y el destinatario.

- El usuario puede exportar un curso que tenga almacenado en la aplicación, obteniendo un archivo YAML que lo almacena.
---
# Casos de uso

| ID      |  Caso de Uso          | Actor      | Descripción                                                   |
|---------|-----------------------|------------|---------------------------------------------------------------|
| Caso 1  | Registro usuario      | Usuario    | Usuario  crea una cuenta.                                     |
| Caso 2  | Inicio sesión         | Usuario    | Usuario accede a su cuenta.                                   |
| Caso 3  | Importar curso        | Usuario    | Usuario añade un curso a su cuenta.                           |
| Caso 4  | Seleccionar curso     | Usuario    | Usuario realiza curso.                                        |
| Caso 5  | Reanudar curso        | Usuario    | Usuario continua curso desde donde lo dejó.                   |
| Caso 6  | Ver estadísticas      | Usuario    | Usuario ve su estadísticas generales.                         |
| Caso 7  | Cambiar imagen        | Usuario    | Usuario cambia su imagen en la aplicación.                    |
| Caso 8  | Compartir curso       | Usuario    | Usuario comparte curso con otro usuario desde la aplicación.  |
| Caso 9  | Exportar curso        | Usuario    | Usuario exporta curso desde la aplicación.                    |

## Descripción casos de uso

### Caso 1:
Actor: Usuario

**Flujo básico:**
1. Usuario abre la ventana de registro.
2. Usuario introduce sus datos.
3. Sistema registra a usuario en el sistema con los datos proporcionados.
4. Sistema muestra mensaje indicado resultado de la operación.

Alternativas:

- 3a: Sistema rechaza los datos porque nombre y/o teléfono ya están siendo utilizados.

Precondición: Usuario no está registrado en el sistema.

### Caso 2:
Actor: Usuario 

**Flujo básico:**
1. Usuario introduce sus datos.
2. Sistema comprueba los datos.
3. Sistema lanza la página principal con el usuario introducido cargado.

Alternativas:

- 3a: Sistema lanza mensaje indicando que los datos son incorrectos.

Precondición: Usuario está registrado en el sistema.

### Caso 3:
Actor: Usuario 

**Flujo básico:**
1. Usuario selecciona opción importar curso.
2. Sistema muestra ventana de selección.
3. Usuario selecciona archivo con el curso.
4. Sistema lee archivo y lo añade lista del usuario.
5. Sistema notifica resultado a usuario.

Alternativas:

- 2a: Hay un error en la lectura y sistema aborta la operación.


### Caso 4:
Actor: Usuario

**Flujo básico:**
1. Usuario selecciona botón Comenzar curso.
2. Sistema muestra ventana de diálogo.
3. Usuario selecciona curso y estrategia a realizar.
4. Sistema abre ventana mostrando las preguntas según estrategia escogida.
5. (En bucle) Usuario responde a las preguntas.
6. (En bucle) Sistema comprueba respuesta y guarda resultado.
7. Usuario termina el curso.
8. Sistema muestra resultados del curso.

Alternativas:
- 4a: Sistema no recibe datos y aborta operación.
- 5b: Usuario abandona curso sin responder todas las preguntas.
- 6b: Sistema vuelve a la página principal sin mostrar resultados.
- 4c: Sistema detecta curso finalizado y muestra ventana de diálogo con estadísticas de últimos resultados preguntando si desea reiniciar.
- 5d: Usuario selecciona no reiniciar.
- 6d: Sistema vuelve a página principal.

Precondición: Hay cursos no comenzados 

### Caso 5:
Actor: Usuario

**Flujo básico:**
1. Usuario selecciona curso y estrategia a realizar, siendo el curso uno no finalizado.
2. Sistema muestra ventana de diálogo indicando si quiere recomenzar o continuar curso.
3. Usuario escoge continuar curso.
4. Sistema muestra preguntas restantes para que el usuario las conteste.
5. (En bucle) Usuario contesta las preguntas.
6. (En bucle) Sistema comprueba respuesta y guarda resultado.
7. Usuario termina el curso.
8. Sistema muestra resultado del curso.

Alternativas:

- 3a: Usuario escoge recomenzar curso.
- 4a: Sistema reinicia curso y muestra todas las preguntas del curso para que el usuario las conteste.
- 6b: Usuario sale del curso sin terminarlo.
- 7b: Sistema vuelve a la página principal sin mostrar resultados.

Precondición: Hay cursos que no están completamente resueltos.


### Caso 6:
Actor: Usuario

**Flujo básico:**
1. Usuario ve sus estadísticas en la ventana principal.


### Caso 7:
Actor: Usuario

**Flujo básico:**
1. Usuario hace doble click sobre su imagen.
2. Sistema muestra ventana de diálogo.
3. Usuario introduce URL de nueva imagen.
4. Sistema carga nueva imagen.

Alternativas:

- 3a: Usuario no introduce ninguna URL.
- 4a: Sistema carga imagen por defecto del sistema.


### Caso 8:
Actor: Usuario

**Flujo básico:**
1. Usuario introduce usuario (nombre o teléfono) al que compartir curso.
2. Usuario selecciona curso que compartir.
3. Sistema comprueba existencia de usuario y comparte curso.
4. Sistema muestra resultado.

Alternativa:
- 3a: Sistema no encuentra usuario y aborta operación.
- 3b: Sistema no recibe curso para compartir y aborta operación.

### Caso 9:
Actor: Usuario

**Flujo básico:**
1. Usuario selecciona curso.
2. Sistema crea archivo con el curso en formato YAML.

Precondición: Hay un curso en la lista de cursos del usuario.

---
## Modelo de dominio

El modelo de la aplicación se muestra en la siguiente imagen:

![Modelo del dominio](Modelo/Modelo%20dominio.png)

---
## NOTAS

- En la carpeta raíz del proyecto tenemos archivos .yaml con una serie de cursos de prueba que contienen al menos una pregunta de cada tipo,
  como referencia para escribir un curso nuevo, además de realizar las pruebas/comprobaciones pertinentes de la aplicación.
  
- Para la persistencia se usan las tecnologías propuestas por la asignatura (JPA API, Hibernate y SQLite).

- Para la lectura de archivos YAML se usa la librería Jackson (todas las librerías usadas se muestran en el POM).
