Add-Type -AssemblyName System.Web.Extensions
$json = Get-Content 'c:/Users/pulli/MCreatorWorkspaces/cannon/src/main/resources/assets/cannon/geo/compassgecko.geo.json' -Raw | ConvertFrom-Json
$xs = New-Object System.Collections.Generic.List[double]
$ys = New-Object System.Collections.Generic.List[double]
$zs = New-Object System.Collections.Generic.List[double]
foreach ($geom in $json.'minecraft:geometry') {
    foreach ($bone in $geom.bones) {
        if ($bone.cubes) {
            foreach ($cube in $bone.cubes) {
                $origin = $cube.origin
                $size = $cube.size
                $xs.Add($origin[0])
                $xs.Add($origin[0] + $size[0])
                $ys.Add($origin[1])
                $ys.Add($origin[1] + $size[1])
                $zs.Add($origin[2])
                $zs.Add($origin[2] + $size[2])
            }
        }
    }
}
$minX = ($xs | Measure-Object -Minimum).Minimum
$maxX = ($xs | Measure-Object -Maximum).Maximum
$avgX = ($xs | Measure-Object -Average).Average
$minY = ($ys | Measure-Object -Minimum).Minimum
$maxY = ($ys | Measure-Object -Maximum).Maximum
$avgY = ($ys | Measure-Object -Average).Average
$minZ = ($zs | Measure-Object -Minimum).Minimum
$maxZ = ($zs | Measure-Object -Maximum).Maximum
$avgZ = ($zs | Measure-Object -Average).Average
Write-Output ("minX={0} maxX={1} avgX={2}" -f $minX,$maxX,$avgX)
Write-Output ("minY={0} maxY={1} avgY={2}" -f $minY,$maxY,$avgY)
Write-Output ("minZ={0} maxZ={1} avgZ={2}" -f $minZ,$maxZ,$avgZ)
