# TALLER DE VERIFICACIÓN DE CONOCIMIENTOS TÉCNICOS

Construir un "Reflective ChatGPT". La solución consta de un servidor que responde a solicitudes HTTP POST y/o GET, y un cliente JS que envía los comandos y muetra las respuestas. El api permite explorar clases del API de java. Cuando el usuario solicita información de una clase el chat le responde con el nombre de la clase, la lista de los campos declarados en la clase y la lista de los métodos declarados en la clase. Además el API debe permitir invocar y mostrar la salida de métodos estáticos con 0, 1 o 2 parámetros. Los parámetros de entrada pueden ser numéricos o Strings.

Los comandos que soporta el chat son los siguientes:
1. Class([class name]): Retorna una lista de campos declarados y métodos declarados
2. invoke([class name],[method name]): retorna el resultado de la invocación del método.  Ejemplo: unaryInvoke(java.lang.System, getenv).
3. unaryInvoke([class name],[method name],[paramtype],[param value]): retorna el resultado de la invocación del método. paramtype = int | double | String.
Ejemplos:
- unaryInvoke(java.lang.Math, abs, int, 3)
- unaryInvoke(java.lang.Integer, valueOf, String, "3")
3. binaryInvoke([class name],[method name],[paramtype 1],[param value], [paramtype 1],[param value],): retorna el resultado de la invocación del método. paramtype = int | double | String. Ejemplos:
- binaryInvoke(java.lang.Math, max, double, 4.5, double, -3.7)
- binaryInvoke(java.lang.Integer,  add, int, 6, int, -3)
 
**** 
## Empezando

### 🛠️ Abre y ejecuta el proyecto

**1. Para empezar se clona el repositorio colocando el siguiente comando**

```
git clone https://github.com/carol695/PARCIAL-AREP.git
```
**2. Ya clonado el repositorio abrimos el laboratorio utilizando cualquier de los siguientes IDE.**

* Intellij.
* eclipse.
* visual Studio code. 


3. Una vez descargado el repositorio nos dirigimos al directorio raiz del proyecto y ejecutamos el comando:

```
mvn clean package exec:java
```

4. Entrar al browser y colocar localhost:35000/cliente
****
### :chart_with_downwards_trend: Prerrequisitos

-   [Git](https://git-scm.com/downloads) - Sistema de control de versiones
-   [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias
-   [Java 8](https://www.java.com/download/ie_manual.jsp) - Entorno de desarrollo
-   [Intellij Idea](https://www.jetbrains.com/es-es/idea/download/) (Opcional)

****

### :bulb: Construido con

* [Maven](https://maven.apache.org/) - Dependency Management

## :mag_right: Versionamiento

Para definir el versionamiento se pudo observar los tags del repositorio, y el versionaiento es 1.0 

## :woman: Actores

* **Carol Tatiana Cely Rodriguez** 
