
Diagrama UML [Link de imagen](https://ibb.co/twrMv3wT).

# 📌 Configuración antes de ejecutar el programa

Antes de correr la aplicación, asegúrate de seguir estos pasos para evitar errores de configuración.

---

## ✅ 1. Verificar **Project Structure** en IntelliJ IDEA

- Abre **IntelliJ IDEA** y revisa la estructura del proyecto.
- Confirma que las librerías necesarias estén correctamente agregadas:

### Librerías requeridas:
- **java-json.rar**  
- **Gson 2.10.1** *(esta versión es obligatoria)*  
  👉 [Descargar Gson 2.10.1](https://github.com/google/gson/releases/tag/gson-2.10.1)

---

## ✅ 2. Instalar y configurar **JavaFX SDK**

- Es indispensable tener el **JavaFX SDK 25** instalado.  
  👉 https://openjfx.io/

- Copia la ruta del SDK en tu sistema. Ejemplo:
``E:\Facultad\2Cuatrimestre\javafx-sdk-25\lib``

Dentro de esta carpeta se encuentran las librerías necesarias para JavaFX.

---

## ✅ 3. Configurar **Run/Debug Configurations** en IntelliJ

1. Ve a **Run → Edit Configurations**.
2. Agrega una nueva configuración:
   - **Tipo:** Application
   - **Main Class:** `Launcher.java`

3. En **VM Options**, pega lo siguiente (¡cambia la ruta por la tuya!):

[COPIAR y REMPLAZAR EL PATH] ``--module-path "E:\Facultad\2Cuatrimestre\javafx-sdk-25\lib" --add-modules javafx.controls,javafx.fxml,javafx.web --enable-native-access=javafx.graphics --add-exports web.balneariotorreonapp.ui=javafx.graphics --add-reads web.balneariotorreonapp=jdk.jsobject``

---

## ✅ 4. Requisitos del sistema
- **Java SDK 25**  
- **IntelliJ IDEA** (última versión recomendada)  
- Librerías mencionadas arriba.

---

### 🔗 Recursos útiles
- https://openjfx.io/
- https://www.jetbrains.com/idea/download/
- https://github.com/google/gson/releases/tag/gson-2.10.1

---

## ▶️ Cómo ejecutar
1. Configura el **Project Structure**.
2. Instala y apunta al **JavaFX SDK**.
3. Ajusta las **Run Configurations**.
4. Ejecuta la clase `Launcher.java`.

---

💡 **Tip:** Si tienes problemas con JavaFX, revisa que la ruta en `--module-path` sea correcta y que incluya la carpeta `lib` del SDK.

