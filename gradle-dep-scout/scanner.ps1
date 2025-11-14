# gradle-dep-scout klasöründen bir üst klasöre çık
$basePath = Resolve-Path ".."

# Üst klasördeki tüm .java dosyalarını alt klasörlerle birlikte tara
$javaFiles = Get-ChildItem -Path $basePath -Recurse -Filter *.java

# import satırlarını topla
$imports = @()
foreach ($file in $javaFiles) {
    $lines = Get-Content $file.FullName
    foreach ($line in $lines) {
        if ($line -match '^import ') {
            $clean = $line -replace '^import ', '' -replace ';$',''
            $imports += $clean
        }
    }
}

# Tekrarsız hale getir ve imports.txt'ye yaz
$imports | Sort-Object -Unique | Set-Content "imports.txt"