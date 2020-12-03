# Clima
Ejemplo básico de una Aplicación de clima con el objetivo de implementar navegación entre actividades, paso de información por medio de intents, uso de la librería de Volley para realizar una petición http a una API y poder parsear la respuesta con la ayuda de la implementación de la librería Gson.

Notas:
Verificar el AndroidManifest.xml tenga los siguientes permisos:
```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET"/>
```
Para evitar el error de texto sin cifrar (Cleartext HTTP traffic not permitted
) cuando se realiza la petición colocar en el AndroidManifest.xml ya que a partir de Android  9 Api 28 esta funcion viene deshabilitada por defecto.
```xml
<application

android:usesCleartextTraffic="true">

</application>
```
La actividad principal que se carga es la de Ciudades.kt, para esto se modifico la prioridad en el AndroidManifest.xml 
```xml
<application
.
.
.
android:usesCleartextTraffic="true">
  <activity
      android:name="com.ulisesdiaz.clima.Ciudades"
      android:label="@string/title_activity_ciudades">
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
          <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
  </activity>
.
.
.
</application>
```
Solicitar una apikey  en https://openweathermap.org/  

Importar las librerías en el gradle

```
implementation 'com.android.volley:volley:1.1.0'
implementation 'com.google.code.gson:gson:2.8.5'
```
Para usar el ejemplo de manera estática sin realizar ninguna petición Http, se debe de modificar la clase Ciudades.kt, en lugar de pasar el código de la ciudad que provee  openweathermap se debe remplazar por:

```kotlin
intent.putExtra(TAG, "ciudad-mexico")  
intent.putExtra(TAG, "ciudad-berlin") 
intent.putExtra(TAG, "ciudad-shanghai")
```
Posteriormente en el MainActivity.kt des-comentar el código siguiente

```kotlin
val ciudadMX = Ciudad("Ciudad de México", 15, "Soleado")
val ciudadBerlin = Ciudad("Ciudad de Berlin", 30, "Cielo despejado")
val ciudadShanghai = Ciudad("Ciudad de Shanghai", 13, "Lluvia")

if (ciudad == "ciudad-mexico"){
    txtCiudad?.text = ciudadMX.nombre;
    txtGrados?.text = ciudadMX.grados.toString() + "°";
    txtEstatus?.text = ciudadMX.estatus
}else if (ciudad == "ciudad-berlin"){
    txtCiudad?.text = ciudadBerlin.nombre;
    txtGrados?.text = ciudadBerlin.grados.toString() + "°";
    txtEstatus?.text = ciudadBerlin.estatus
}else if(ciudad == "ciudad-shanghai"){
    txtCiudad?.text = ciudadShanghai.nombre;
    txtGrados?.text = ciudadShanghai.grados.toString() + "°";
    txtEstatus?.text = ciudadShanghai.estatus
}else{
    Toast.makeText(this, "NO se encontro la ciudad", Toast.LENGTH_LONG)
}
```
Al final comentar la invocación al método requestHttpVolley()

```kotlin
/*
if (NetWork.isNetwork(this)){
    // Ejecutar codigo http
    requestHttpVolley("http://api.openweathermap.org/data/2.5/weather?id=" + ciudad
            +"&appid=434c7afe4cc095e201350d4143652345&units=metric&lang=es")
}else{
    // Mostrar mensaje de error
    Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT)
}
*/
```
