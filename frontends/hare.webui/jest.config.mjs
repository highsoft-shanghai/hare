import {quasarEsModulesPackageNames} from '@quasar/quasar-app-extension-testing-unit-jest/jest-preset.mjs';

/** @type {import('jest').Config} */
export default {
  preset: '@quasar/quasar-app-extension-testing-unit-jest',
  coverageProvider: 'v8',
  collectCoverage: true,
  coverageThreshold: {
    global: {
      branches: 100,
      functions: 100,
      lines: 100,
      statements: 100
    },
  },
  transform: {
    [`^(${quasarEsModulesPackageNames}).+\\.js$`]: 'babel-jest',
    '^.+\\.(ts|js|html)$': [
      'ts-jest',
      {
        // Remove if using `const enums`
        // See https://kulshekhar.github.io/ts-jest/docs/getting-started/options/isolatedModules/
        isolatedModules: true,
      },
    ],
  },
  testMatch: [
    '<rootDir>/test/jest/__tests__/**/*.(spec|test).+(ts|js)?(x)',
    '<rootDir>/src/**/*.(spec|test).+(ts|js)?(x)',
  ],
  reporters: [
    'default',
    ['./node_modules/jest-html-reporter', {'pageTitle': 'Fusion WebUI Test Report', outputPath: 'test/jest/reports/report.html'}]
  ]
};
