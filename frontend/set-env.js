const fs = require('fs');
const path = require('path');

// Carga el archivo .env que está un nivel arriba (en la raíz del proyecto general)
require('dotenv').config({ path: path.resolve(__dirname, '../.env') });

const appPort = process.env.APP_PORT || '8080';
const envDirectory = './src/environments';
const targetPath = `${envDirectory}/environment.ts`;

// Generamos el archivo con tipado explícito para evitar el error de TypeScript
const envConfigFile = `// Este archivo es autogenerado por set-env.js
export const environment: { production: boolean; apiUrl: string } = {
  production: false,
  apiUrl: 'http://localhost:${appPort}/neo'
};
`;

if (!fs.existsSync(envDirectory)) {
  fs.mkdirSync(envDirectory, { recursive: true });
}

fs.writeFileSync(targetPath, envConfigFile);
console.log(`✅ Variables de entorno generadas con éxito usando el puerto: ${appPort}`);