// ============= Scripts para executar testes (scripts/run-tests.sh) =============
#!/bin/bash

echo "🧪 Executando Testes do LiterAlura"
echo "=================================="

echo "📋 1. Testes Unitários..."
mvn test -Dtest="**/*Test" -DfailIfNoTests=false

echo ""
echo "🔗 2. Testes de Integração..."
mvn test -Dtest="**/*IntegrationTest" -DfailIfNoTests=false

echo ""
echo "🐳 3. Testes com TestContainers..."
mvn test -Dtest="**/*TestContainerTest" -DfailIfNoTests=false

echo ""
echo "🌐 4. Testes de API Real (opcional)..."
echo "Para executar testes com API real, execute:"
echo "INTEGRATION_TEST=true mvn test -Dtest=\"**/ApiIntegrationTest\""

echo ""
echo "📊 5. Relatório de Cobertura..."
mvn jacoco:report

echo ""
echo "✅ Testes concluídos!"
echo "Relatório de cobertura disponível em: target/site/jacoco/index.html"