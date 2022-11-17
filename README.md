# GJC - GeoJSON Coalescer
A basic software to coalesce GeoJSON files for VATSIM CRC

----------------

###PURPOSE

When creating GeoJSON maps for VATSIM's CRC, it can be tedious (and error-prone) 
to manually coalesce all your individual GeoJSON layers into one master file to upload to the vNAS
admin panel.

This program allows you to select all your individual GeoJSON layer files and automagically build 
the master file that is required for CRC, reducing workload and likelihood of GeoJSON 
formatting errors.


###HOW TO USE
1) Download GJC from the [RELEASES PAGE](https://github.com/ksnifte/vatsimGJC/releases/tag/1.0).
2) Install [Java Runtime 19](https://www.oracle.com/java/technologies/downloads/) on your computer.
3) Run GJC.
4) Enter the desired master GeoJSON file name in the text field.  *For example, in the ZMA ARTCC, 
we would name it `PBI Cab Map 2211`, for the KPBI map developed in November 2022.*
5) Click "ADD FILES" and select all the GeoJSON files you wish to import by `Ctrl+Click`ing on the
files.
6) Click "OKAY".

If you received a popup that said "Success!", then your master GeoJSON file was successfully
generated and is located in the same directory as the GJC executable.

If you did NOT receive a popup that said "Success!", then an error occurred.  Check the log in 
the main GJC window for errors.  If you do not see an error there, please contact me 
at [ksnifte@gmail.com](mailto:ksnifte@gmail.com) for support.

