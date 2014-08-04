@echo off 
title Libgdx Build Tool v0.0.1
:begin
cd C:\Users\Alex\Documents\Dev\LevelEditor
color 0a
echo.
echo Libgdx Build Tool
echo 1.run desktop
echo 2.distribute desktop
echo 3.run android
echo 4.distribute android
echo 5.run html
echo 6.distribute html
echo 0.close
echo.

set /p a=
IF %a%==1 call gradlew desktop:run

IF %a%==2 call gradlew desktop:dist

IF %a%==3 call gradlew android:installDebug android:run

IF %a%==4 call gradlew android:assembleRelease

IF %a%==5 call gradlew html:superDev

IF %a%==6 call gradlew html:dist call python -m SimpleHTTPServer start http://localhost:8000

IF %a%==0 exit 0

goto begin