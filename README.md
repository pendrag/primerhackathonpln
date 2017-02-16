# Patent Commander

## Introducción

Participación de la Universidad de Jaén en el [I Hackathon de Tecnologías del Lenguaje Humano](http://www.primerhackathonpln.es/) (2017)

Nuestra propuesta propone un sistema de búsqueda de patentes relevantes a un periodo de tiempo dado (en este prototipo, el periodo es de un año). Dicha relevancia está estimada en base a la producción científica de ese año, mediante un modelado del tiempo usando el Modelo de Espacio Vectorial clásico.

## Fuentes de datos

Los datos que se van a utilizar son

* [The DBLP Computer Science Bibliography](https://datahub.io/dataset/dblp). Este conjunto de datos contiene 1.8 registros bibliográficos sobre artículos científicos en Ciencias de la Computación.

* [USPTO](https://www.uspto.gov/) United States Patents and Trademarks Office. Patentes norte-americanas, de los años 2005 y 2006, proporcionadas por la organización del Hackathon.

## Recursos utilizados

* Python: Lenguaje de *script*
* NLTK: Potente biblioteca para Python para Procesamiento del Lenguaje Natural
* Lucene: Motor de recuperación de información escrito en Java
* Flask: Servidor de aplicaciones web para Python

## Descripcin del sistema

El sistema funciona de la siguiente manera (tanto para títulos como para descripciones se hace un filtrado de palabras vacías).

**Indexar DBLP**

1. Extraemos los registros bibliográficos de DBLP (campos *title* y *ID*)
1. Generamos un documento por año
1. Indexamos la colección así generada con Lucene 

**Indexar USPTO**

1. Indexamos las patentes sobre los campos *id* y *description*

**Generar consulta para un año**

1. Extraemos el modelo de espacio vectorial para un año (documento)
1. Generamos una consulta (*query*) dándole pesos a los primero términos de model anterior usando *QueryBoost* en Lucene
1. Lanzamos esa consulta sobre el índice de pantentes y obtenemos un ranking de las mismas
