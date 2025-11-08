$base = "shopstack"

# Yardımcı fonksiyon: klasör oluştur
function New-Folder($path) {
    if (-not (Test-Path $path)) {
        New-Item -ItemType Directory -Path $path | Out-Null
    }
}

# Yardımcı fonksiyon: dosya oluştur
function New-File($path) {
    if (-not (Test-Path $path)) {
        New-Item -ItemType File -Path $path | Out-Null
    }
}

# Klasör yapısını tanımla
$folders = @(
    "$base/common/src/main/java/com/shopstack/common/exception",
    "$base/common/src/main/java/com/shopstack/common/security",
    "$base/common/src/main/java/com/shopstack/common/dto",
    "$base/common/src/main/java/com/shopstack/common/utils",
    "$base/common/src/main/resources",
    "$base/common/test/java",

    "$base/auth-service/src/main/java/com/shopstack/auth/controller",
    "$base/auth-service/src/main/java/com/shopstack/auth/service",
    "$base/auth-service/src/main/java/com/shopstack/auth/service/impl",
    "$base/auth-service/src/main/java/com/shopstack/auth/repository",
    "$base/auth-service/src/main/java/com/shopstack/auth/config",
    "$base/auth-service/src/main/java/com/shopstack/auth/domain",
    "$base/auth-service/src/main/java/com/shopstack/auth/mapper",
    "$base/auth-service/src/main/resources",
    "$base/auth-service/test/java",

    "$base/catalog-service/src/main/java/com/shopstack/catalog/controller",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/service",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/service/impl",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/repository",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/config",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/domain",
    "$base/catalog-service/src/main/java/com/shopstack/catalog/mapper",
    "$base/catalog-service/src/main/resources",
    "$base/catalog-service/test/java",

    "$base/order-service/src/main/java/com/shopstack/order/controller",
    "$base/order-service/src/main/java/com/shopstack/order/service",
    "$base/order-service/src/main/java/com/shopstack/order/service/impl",
    "$base/order-service/src/main/java/com/shopstack/order/repository",
    "$base/order-service/src/main/java/com/shopstack/order/domain",
    "$base/order-service/src/main/java/com/shopstack/order/config",
    "$base/order-service/src/main/java/com/shopstack/order/outbox",
    "$base/order-service/src/main/java/com/shopstack/order/mapper",
    "$base/order-service/src/main/resources",
    "$base/order-service/test/java",

    "$base/inventory-service/src/main/java/com/shopstack/inventory/controller",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/service",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/service/impl",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/repository",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/domain",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/config",
    "$base/inventory-service/src/main/java/com/shopstack/inventory/mapper",
    "$base/inventory-service/src/main/resources",
    "$base/inventory-service/test/java",

    "$base/api-gateway/src/main/java/com/shopstack/gateway/config",
    "$base/api-gateway/src/main/java/com/shopstack/gateway/filters",
    "$base/api-gateway/src/main/resources",
    "$base/api-gateway/test/java",

    "$base/docker",
    "$base/helm/shopstack/templates",
    "$base/k8s/base/postgres",
    "$base/k8s/base/mongo",
    "$base/k8s/overlays/dev",
    "$base/k8s/overlays/stage",
    "$base/k8s/overlays/prod",
    "$base/docs/openapi",
    "$base/.github/workflows"
)

# Dosyaları tanımla
$files = @(
    "$base/auth-service/src/main/resources/application.yml",
    "$base/api-gateway/src/main/resources/application.yml",

    "$base/docker/docker-compose.dev.yml",
    "$base/docker/Dockerfile.auth",
    "$base/docker/Dockerfile.catalog",
    "$base/docker/Dockerfile.order",
    "$base/docker/Dockerfile.inventory",
    "$base/docker/Dockerfile.gateway",

    "$base/helm/shopstack/Chart.yaml",
    "$base/helm/shopstack/values.yaml",
    "$base/helm/shopstack/templates/deployment.yaml",
    "$base/helm/shopstack/templates/service.yaml",
    "$base/helm/shopstack/templates/ingress.yaml",
    "$base/helm/shopstack/templates/hpa.yaml",
    "$base/helm/shopstack/templates/configmap.yaml",
    "$base/helm/shopstack/templates/secrets.yaml",

    "$base/k8s/base/namespace.yaml",
    "$base/k8s/base/postgres/statefulset.yaml",
    "$base/k8s/base/postgres/service.yaml",
    "$base/k8s/base/postgres/pvc.yaml",
    "$base/k8s/base/mongo/statefulset.yaml",
    "$base/k8s/base/mongo/service.yaml",
    "$base/k8s/base/mongo/pvc.yaml",

    "$base/k8s/overlays/dev/kustomization.yaml",
    "$base/k8s/overlays/stage/kustomization.yaml",
    "$base/k8s/overlays/prod/kustomization.yaml",

    "$base/docs/architecture.md",
    "$base/docs/openapi/auth-api.yaml",
    "$base/docs/openapi/catalog-api.yaml",
    "$base/docs/openapi/order-api.yaml",
    "$base/docs/openapi/inventory-api.yaml",

    "$base/.github/workflows/ci.yml"
)

# Klasörleri oluştur
foreach ($folder in $folders) {
    New-Folder $folder
}

# Dosyaları oluştur
foreach ($file in $files) {
    New-File $file
}

Write-Host "✅ Shopstack klasör yapısı başarıyla oluşturuldu."