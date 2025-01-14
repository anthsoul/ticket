@echo off
setlocal enabledelayedexpansion

rem Đường dẫn video đầu vào
set input="C:\workspace\java\ticket\src\main\resources\video\input.mp4"
rem Thư mục lưu các đoạn video .ts
set output_dir="C:\workspace\java\ticket\src\main\resources\hls"

rem Kiểm tra và tạo thư mục nếu chưa có
if not exist %output_dir% mkdir %output_dir%

rem Tạo file playlist .m3u8
set playlist="%output_dir%\playlist.m3u8"
echo #EXTM3U > %playlist%
echo #EXT-X-TARGETDURATION:360 >> %playlist%
echo #EXT-X-MEDIA-SEQUENCE:0 >> %playlist%

rem Cắt video thành các file .ts
for /l %%i in (0,1,19) do (
    set /a start=%%i * 360
    rem Tạo file .ts cho mỗi đoạn
    ffmpeg -i %input% -ss !start! -t 360 -c copy -f mpegts "%output_dir%\output_%%i.ts"

    rem Thêm file .ts vào playlist
    echo #EXTINF:360.0, >> %playlist%
    echo output_%%i.ts >> %playlist%
)

rem Kết thúc playlist
echo #EXT-X-ENDLIST >> %playlist%

echo Done!
pause
