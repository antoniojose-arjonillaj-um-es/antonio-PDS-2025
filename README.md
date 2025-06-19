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
## Características adicionales del sistema desarrolladas

- El usuario tiene una imagen, la cual puede cambiar al hacer doble click sobre esta.

- El usuario dispone de una serie de tickets (10 como máximo) los cuales puede gastar para realizar cursos.
Estos tickets se calculan al iniciar sesión a partir desde la fecha de último inicio de sesión.

- El usuario puede compartir un curso que tenga importado en la aplicación, indicando el curso a compartir y el destinatario.

- El usuario puede exportar un curso que tenga almacenado en la aplicación, obteniendo un archivo YAML que lo almacena.
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
## NOTAS

- En la carpeta raíz del proyecto tenemos archivos .yaml con una serie de cursos de prueba que contienen al menos una pregunta de cada tipo,
  como referencia para escribir un curso nuevo, además de realizar las pruebas/comprobaciones pertinentes de la aplicación.
  
- Para la persistencia se usan las tecnologías propuestas por la asignatura (JPA API, Hibernate y SQLite).

- Para la lectura de archivos YAML se usa la librería Jackson (todas las librerías usadas se muestran en el POM).
