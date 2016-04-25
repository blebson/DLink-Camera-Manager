## D-Link Camera Manager (Connect).
----

This SmartApp will allow you to install and manage multiple D-Link Camera Devices.Finally, a one-stop shop for all of your D-Link IP camera needs.

I have created these devices in order to add additional functionality to existing supported D-Link cameras and to add compatibility for many that are not natively supported. All cameras feature In-App Video streaming, snapshots and the ability to toggle motion detection. Some cameras support much much more, see the feature and compatibility chart below!

If you don't see a camera on the list that you would like me to add; feel free to leave a comment below and I will work to incorporate it!

----

<img src="http://community.smartthings.com/uploads/default/original/3X/6/e/6eabc803caab1c0cfa94d853c0698708b69357a5.png" width="281" height="500">
----------

## Features:

 - **V** - In-App Video streaming w/o sound
 - **VS** - In-App Video streaming w/ sound
 - **I** - Still Image Capture and Display
 - **PT** - Full Pan/Tilt support
 - **H** - Move to 'Home' location
 - **P** - Move to Preset location (by name)
 - **M** -  Turn on/off motion detection (Motion Only)
 - **PIR** - Turn on/off PIR sensor (PIR Only)
 - **MS** - Adjust motion sensitivity (Motion & PIR combined)
 - **NV** - Toggle Night Vision On/Off/Auto
 - **VR** - Turn on/off Video Recording to SD card


----------

## Currently Supported Cameras:

**Fixed Cameras:**

 - DCS-930L(\*) - **V, I, M, MS**
 - DCS-931L(\*) - **V, I, M, MS**
 - DCS-932L(\*) - **V, I, M, MS, NV**
 - DCS-933L(\*)- **V, I, M, MS, NV**
 - DCS-934L(\*)- **V, I, M, MS, NV**
 - DCS-935L - **VS, I, M, MS, NV**
 - DCS-942L - **VS, I, M, PIR, MS, NV, VR**
 - DCS-2132L(\*) - **VS, I, M, PIR, MS, NV, VR**
 - DCS-2310L(\*) - **VS, I, M, PIR, MS, NV, VR**
 - DCS-2330L(\*) - **VS, I, M, PIR, MS, NV, VR**

**PTZ Cameras:**

 - DCS-5009L - **V, I, PT, H, P, M, MS, NV**
 - DCS-5020L - **V, I, PT, H, P, M, MS, NV**
 - DCS-5029L - **VS, I, PT, H, P, M, PIR, MS, NV, VR**
 - DCS-5211L(\*) - **VS, I, PT, H, P, M, PIR, MS, NV, VR**
 - DCS-5222L - **VS, I, PT, H, P, M, PIR, MS, NV, VR**

_(*) - Camera has not been formally tested, please report any issues._

----------


## External Commands:
**Please Note: Not all cameras support all commands.**

 - **motionOn()**  -  Video Motion Detection On
 - **motionOff()**  -  Video Motion Detection Off
 - **pirOn()**  -  PIR Motion Detection On
 - **pirOff()**  -  PIR Motion Detection Off
 - **nvOn()**  -  Night Vision On
 - **nvOff()**  -  Night Vision Off
 - **nvAuto()**  -  Night Vision Auto
 - **vrOn()**  -  Local Video Recording On
 - **vrOff()**  -  Local Video Recording Off
 - **left()**  -  Move Camera Left
 - **right()**  -  Move Camera Right
 - **up()**  -  Move Camera Up
 - **down()**  -  Move Camera Down
 - **home()**  -  Move to 'Home' position
 - **presetOne()**  -  Move to Preset position 1 defined in DTH settings
 - **presetTwo()**  -  Move to Preset position 2 defined in DTH settings
 - **presetThree()**  -  Move to Preset position 3 defined in DTH settings
 - **presetCommand( String _preset-name_ )**  -  Move to Preset position (position name passed as a string)



----------

## Prerequisites:

 - Fully configured compatible D-Link IP camera on the same local network as your ST hub
 - Motion detection configured within local advanced camera settings (for motion detection / PIR sensor) _(where applicable)_
 - Events created in local advanced camera settings to respond to the motion trigger (email/FTP/SD Image/video) _(where applicable)_
 - SD Card installed (for local video recording) _(where applicable)_
 - Video settings configured within local advanced camera settings _(where applicable)_
 - Preset and Home positions configured within the local advanced camera settings _(where applicable)_


----------

## Installation Instructions:

 1. Add the Github Repo to your IDE with the following settings:
  - Owner: _blebson_
  - Name: _DLink-Camera-Manager_
  - Branch: _master_

 2. Under 'My Device Handlers' in the SmartThings IDE click <img src="http://community.smartthings.com/uploads/default/original/3X/c/6/c65c0c35b43c714b2d305af2d2e48b2bf2481a0f.png" width="165" height="41">
 3. Select _DLink-Camera-Manager (master)_ from the drop-down menu.
 4. Select ALL of the camera devices from the 'New' tab.
 5. Click the 'Publish' check-box in the bottom right.
 6. Click on 'Execute Update' 
 7. Go to the "My SmartApps" tab in the IDE and click <img src="http://community.smartthings.com/uploads/default/original/3X/c/6/c65c0c35b43c714b2d305af2d2e48b2bf2481a0f.png" width="165" height="41">
 8. Select _DLink-Camera-Manager (master)_ from the drop-down menu.
 9. Select all 3 SmartApps in the 'New' tab.  
 10. Click on 'Execute Update' 
 11. In your 'My SmartApps' section of IDE, select open: <img src="http://community.smartthings.com/uploads/default/original/3X/d/e/de692467dbb4fcf3d0b6673d8a6a38d39bfbe600.png" width="357" height="38">
 12. When the SmartApp opens, click Publish -> For Me (Do not publish the other two 'Child' SmartApps)
 13. Now, go into the SmartThings App on your phone/tablet
 14. Go to the 'Marketplace' tab in the bottom right.  
 15. Go to the 'SmartApps' tab and select 'My Apps'
 16. Select "D-Link Camera Manager (Connect)"
 17. Select 'Done' in the top right to install the app (configuration comes later)
 18. Go back to the 'Marketplace' and click on the menu in the top right
 19. Click 'SmartApps' and click on the "D-Link Camera Manager (Connect)" app.
 20. From here follow the instructions and start adding your D-Link Cameras!
----------

##Camera Installation Tips/Tricks:

 - The Camera IP and Camera Port are your **local** HTTP IP and Port for the camera
 - The Video IP and Video Port can be either the **local or external** IP and Port for the camera
  - For Cameras that only support video (no audio) use the HTTP IP/Port
    - Default HTTP port is 80
  - For Cameras that support both video and audio use the RTSP IP/Port
    - Default RTSP port is 554
  - _If you use an **internal** VideoIP/Port you will only be able to view video either while on the local network or by using a VPN_
  - _If you use an **external** VideoIP/Port you will need to set up Port Forwarding on your router_
    - **WARNING: By using Port Forwarding you are opening your camera to the internet, this can open it up to unauthorized access by people outside of your network.**
 - The Camera User and Camera Password are the Username/Password used to log into the camera's advanced settings through a web browser.
 - The Camera Presets are the preset number of views that you have already set up in the D-Link camera advanced settings.
----------

## Compatible Custom SmartApps:

 - [**Smart Security Camera**](https://community.smartthings.com/t/release-smart-security-camera-smartapp-now-with-local-video-recording-support/35877) - Allows your camera to follow where the action is and take pictures/video!
 - [**Smart Night Vision**](https://community.smartthings.com/t/release-smart-night-vision/43317) - Tie your camera's night vision to a switch/dimmer so that you always know it's in the correct mode.

----------

##Sample D-Link Camera Views:

**DCS-5222L:** 

<img src="https://community.smartthings.com/uploads/default/original/3X/6/9/690639f38cac333a23c1170eb27af52a81ead81b.png" width="281" height="500"> <img src="http://community.smartthings.com/uploads/default/original/3X/5/b/5bb56ecce614734cff32fdeea75c9a12ac0a4b0a.png" width="281" height="500">

**DCS-5020L:**

<img src="http://community.smartthings.com/uploads/default/original/3X/6/9/6994ebebb174eb4c11d79d765ce8dae553dc82ee.png" width="281" height="500"> <img src="http://community.smartthings.com/uploads/default/original/3X/f/3/f308b0563467fd47ffdb8303e1aacdb1c38fa1a7.png" width="281" height="500">

**DCS-942L:**

<img src="http://community.smartthings.com/uploads/default/original/3X/7/f/7f5f4da87995373d5a65450f75b3d2f6df1d8c29.png" width="281" height="500"> <img src="http://community.smartthings.com/uploads/default/original/3X/1/8/180b1f569c8ae63c6596f0e54583dc19d57a3ec4.png" width="281" height="500">

----------

##Special Thanks and other notes:

I wanted to thank the following people for their inspiration and help with debugging and testing:
 - @pstuart
 - @slagle
 - @RudiP
 - @StrykerSKS   

----------

I developed this in my spare time and am releasing it completely free of charge. Any donations are greatly appreciated. Please note, donations will not get you any special features/treatment but will grant you my eternal gratitude :slight_smile:

[<img src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif">](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=blebson%40gmail%2ecom&lc=US&item_name=SmartThings%20%2d%20D%2dLink%20Camera%20Manager&item_number=DLink%2dCM%2dDonation&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted)

-----


https://github.com/blebson/DLink-Camera-Manager
