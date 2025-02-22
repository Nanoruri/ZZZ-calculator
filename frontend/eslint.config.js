import js from '@eslint/js'
import globals from 'globals'
import esLintPluginReactHooks from 'eslint-plugin-react-hooks'
import esLintPluginReact from 'eslint-plugin-react'
import reactRefresh from 'eslint-plugin-react-refresh'
import tseslint from 'typescript-eslint'

export default tseslint.config(
    {ignores: ['dist', 'node_modules'],},
    [js.configs.recommended,
        ...tseslint.configs.recommended],
    {
        files: ['**/*.{ts,tsx}'],
        languageOptions: {
            ecmaVersion: 2020,
            globals: globals.browser,
        },
        plugins: {
            'react': esLintPluginReact,
            'react-hooks': esLintPluginReactHooks,
            'react-refresh': reactRefresh,
        },
        rules: {
            ...esLintPluginReact.configs.flat.recommended.rules,
            ...esLintPluginReactHooks.configs.recommended.rules,
            'react-refresh/only-export-components': [
                'warn',
                {allowConstantExport: true},
            ],
          'react/react-in-jsx-scope': 'off',  // 이 규칙을 끄기
          'react/jsx-no-target-blank': 'off',  // 이 규칙을 끄기
          '@typescript-eslint/no-unused-vars': 'warn',  // 이 규칙을 끄기
        },
    },
)
