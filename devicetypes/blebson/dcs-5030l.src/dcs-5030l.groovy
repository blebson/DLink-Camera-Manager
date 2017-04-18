/*
 *	D-Link DCS-5030L v1.0.3
 *	Image Capture and Video Streaming courtesy Patrick Stuart (patrick@patrickstuart.com)
 *      5030L updates contributed by Eric S. (@E_sch) 
 *
 *	Copyright 2015 blebson
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *	in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *	for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "DCS-5030L", author: "Ben Lebson", namespace: "blebson") {

		capability "Image Capture"
		capability "Sensor"
		capability "Switch"
		capability "Switch Level"
		capability "Refresh"
		capability "Notification"
		capability "Configuration"
		capability "Video Camera"
		capability "Video Capture"

		attribute "hubactionMode", "string"
		attribute "switch1", "string"
		attribute "switch2", "string"
		attribute "switch3", "string"
		attribute "switch3level", "string"
		attribute "switch4", "string"
		attribute "switch5", "string"
		attribute "switch6", "string"

		command "audioOn"
		command "audioOff"
		command "pirOn"
		command "pirOff"
		command "nvOn"
		command "nvOff"
		command "nvManual"
		command "nvAuto"
		command "vrOn"
		command "vrOff"
		command "left"
		command "right"
		command "up"
		command "down"
		command "home"
		command "presetOne"
		command "presetTwo"
		command "presetThree"
		command "presetCommand"
		command "start"
		command "stop"
		command "vidOn"
		command "vidOff"

	}

	preferences {

	}

	mappings {
 		path("/getInHomeURL") {
 		action:
			[GET: "getInHomeURL"]
 		}
	}

	simulator {

	}

	tiles (scale: 2){
		 multiAttributeTile(name: "videoPlayer", type: "videoPlayer", width: 6, height: 4) {
			tileAttribute("device.switch5", key: "CAMERA_STATUS") {
				attributeState("on", label: "Active", icon: "st.camera.dlink-indoor", action: "vidOff", backgroundColor: "#79b821", defaultState: true)
				attributeState("off", label: "Inactive", icon: "st.camera.dlink-indoor", action: "vidOn", backgroundColor: "#ffffff")
				attributeState("restarting", label: "Connecting", icon: "st.camera.dlink-indoor", backgroundColor: "#53a7c0")
				attributeState("unavailable", label: "Unavailable", icon: "st.camera.dlink-indoor", action: "refresh.refresh", backgroundColor: "#F22000")
			}

			tileAttribute("device.camera", key: "PRIMARY_CONTROL") {
				attributeState("on", label: "Active", icon: "st.camera.dlink-indoor", backgroundColor: "#79b821", defaultState: true)
				attributeState("off", label: "Inactive", icon: "st.camera.dlink-indoor", backgroundColor: "#ffffff")
				attributeState("restarting", label: "Connecting", icon: "st.camera.dlink-indoor", backgroundColor: "#53a7c0")
				attributeState("unavailable", label: "Unavailable", icon: "st.camera.dlink-indoor", backgroundColor: "#F22000")
			}

			tileAttribute("device.startLive", key: "START_LIVE") {
				attributeState("live", action: "start", defaultState: true)
			}

			tileAttribute("device.stream", key: "STREAM_URL") {
				attributeState("activeURL", defaultState: true)
			}
			tileAttribute("device.betaLogo", key: "BETA_LOGO") {
				attributeState("betaLogo", label: "", value: "", defaultState: true)
			}
		}
		carouselTile("cameraDetails", "device.image", width: 6, height: 4) { }

		standardTile("take", "device.image", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state "take", label: "Take", action: "Image Capture.take", icon: "st.camera.camera", backgroundColor: "#FFFFFF", nextState:"taking"
			state "taking", label:'Taking', action: "", icon: "st.camera.take-photo", backgroundColor: "#53a7c0"
			state "image", label: "Take", action: "Image Capture.take", icon: "st.camera.camera", backgroundColor: "#FFFFFF", nextState:"taking"
		}

		standardTile("refresh", "command.refresh", width: 2, height: 2, inactiveLabel: false) {
			state "default", label:'refresh', action:"refresh.refresh", icon:"st.secondary.refresh-icon"
		}
		standardTile("motion", "device.switch", width: 2, height: 2, canChangeIcon: false) {
			state "off", label: 'Motion Off', action: "switch.on", icon: "st.motion.motion.inactive", backgroundColor: "#ccffcc", nextState: "toggle"
			state "toggle", label:'toggle', action: "", icon: "st.motion.motion.inactive", backgroundColor: "#53a7c0"
			state "on", label: 'Motion On', action: "switch.off", icon: "st.motion.motion.active", backgroundColor: "#EE0000", nextState: "toggle"
		}
		 standardTile("PIR", "device.switch2", width: 2, height: 2, canChangeIcon: false) {
			state "off", label: 'PIR Off', action: "pirOn", icon: "st.custom.buttons.rec", backgroundColor: "#ccffcc", nextState: "toggle"
			state "toggle", label:'toggle', action: "", icon: "st.motion.buttons.rec", backgroundColor: "#53a7c0"
			state "on", label: 'PIR On', action: "pirOff", icon: "st.custom.buttons.rec", backgroundColor: "#EE0000", nextState: "toggle"
		}
		standardTile("up", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false, decoration: "flat") {
			state "up", label: "", action: "up", icon: "st.samsung.da.oven_ic_up", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0", icon: "st.samsung.da.oven_ic_up"
		}
		standardTile("left", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false, decoration: "flat") {
			state "left", label: "", action: "left", icon: "st.samsung.da.RAC_4line_01_ic_left", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0", icon: "st.samsung.da.RAC_4line_01_ic_left"
		}
		standardTile("home", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false) {
			state "home", label: "Home", action: "home", icon: "st.Home.home2", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0", icon: "st.Home.home2"
		}
		standardTile("right", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false, decoration: "flat") {
			state "right", label: "", action: "right", icon: "st.samsung.da.RAC_4line_03_ic_right", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0", icon: "st.samsung.da.RAC_4line_03_ic_right"
		}
		standardTile("down", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false, decoration: "flat") {
			state "down", label: "", action: "down", icon: "st.samsung.da.oven_ic_down", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0", icon: "st.samsung.da.oven_ic_down"
		}
		standardTile("presetOne", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false) {
			state "presetOne", label: "1", action: "presetOne", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0"
		}
		standardTile("presetTwo", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false) {
			state "presetTwo", label: "2", action: "presetTwo", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0"
		}
		standardTile("presetThree", "device.switch6", width: 2, height: 2, canChangeIcon: false,	canChangeBackground: false) {
			state "presetThree", label: "3", action: "presetThree", nextState: "moving"
			state "moving", label: "moving", action:"", backgroundColor: "#53a7c0"
		}
		standardTile("nightVision", "device.switch3", width: 2, height: 2, canChangeIcon: false) {
			state "off", label: 'NV Off', action: "nvManual", icon: "st.Weather.weather14", backgroundColor: "#ffff00", nextState: "toggle"
			state "manual", label: 'NV Manual', action: "nvAuto", icon: "st.Weather.weather14", backgroundColor: "#ffff00", nextState: "toggle"
			state "auto", label: 'NV Auto', action: "nvOn", icon: "st.motion.motion.active", backgroundColor: "#ccffcc", nextState: "toggle"
			state "toggle", label:'toggle', action: "", icon: "st.motion.motion.inactive", backgroundColor: "#53a7c0"
			state "on", label: 'NV On', action: "nvOff", icon: "st.Weather.weather4", backgroundColor: "#4169E1", nextState: "toggle"
		}
		standardTile("Video", "device.switch4", width: 2, height: 2, canChangeIcon: false) {
			state "off", label: 'Video Off', action: "vrOn", icon: "st.Entertainment.entertainment9", backgroundColor: "#ccffcc", nextState: "toggle"
			state "toggle", label:'toggle', action: "", icon: "st.Entertainment.entertainment9", backgroundColor: "#53a7c0"
			state "on", label: 'Video On', action: "vrOff", icon: "st.Entertainment.entertainment9", backgroundColor: "#EE0000", nextState: "toggle"
		}
		standardTile("Audio", "device.switch1", width: 1, height: 1, canChangeIcon: false) {
			state "off", label: 'Audio Off', action: "audioOn", icon: "st.Entertainment.entertainment15", backgroundColor: "#ccffcc", nextState: "toggle"
			state "toggle", label:'toggle', action: "", icon: "st.Entertainment.entertainment15", backgroundColor: "#53a7c0"
			state "on", label: 'Audio On', action: "audioOff", icon: "st.Entertainment.entertainment15", backgroundColor: "#EE0000", nextState: "toggle"
		}
		controlTile("levelSliderControl", "device.level", "slider", height: 1, width: 5, inactiveLabel: false, range:"(0..100)") {
			state "level", action:"switch level.setLevel"
		}
		standardTile("videoStart", "device.image", width: 2, height: 2, inactiveLabel: false) {
			state "start", action:"start", icon:"st.Entertainment.entertainment11"
		}
		valueTile("Sensitivity", "device.level", width: 1, height: 1, inactiveLabel: false){
			state "default", label:'${currentValue}%', unit:"%"
		}
		valueTile("LightSensitivity", "device.switch3level", width: 2, height: 2, inactiveLabel: false){
			state "1", label:"Low", unit:""
			state "2", label:"Med", unit:""
			state "3", label:"High", unit:""
			state "inactive", label:"NA", unit:""
		}

		main "motion"
		//details(["videoPlayer", "presetOne", "presetTwo","presetThree", "refresh", "up", "videoStart","cameraDetails", "left", "home", "right", "take", "down", "Video", "levelSliderControl", "Sensitivity", "PIR", "motion", "nightVision", ])
		details(["videoPlayer", 
           "take", "up", "Video",
           "left", "home", "right", 
           "refresh", "down", "nightVision",
           "cameraDetails", 
           "presetOne", "presetTwo","presetThree",
           "LightSensitivity", "motion", "videoStart",
           "levelSliderControl", "Sensitivity" 
           ])
	}
}

def refresh(){
	log.debug "refresh"

	if(!state?.cnt) { state.cnt = 1 }
	def method = "GET"
	def path = "/motion.cgi"
	switch (state.cnt) {
		case 1:
			doCmd(method,path)
			break
		case 2:
			path = "/daynight.cgi"
			doCmd(method,path) 
			break
		default:
			path = "/pantiltcontrol.cgi"
			doCmd(method,path)
			break
	}
	state.cnt += 1
	return

//testing
	audioOn()	// Does not work
	audioOff()	// Does not work
	pirOn()		// Does not work
	pirOff()	// Does not work
	nvOn()
	nvOff()
	nvManual()
	nvAuto()
	vrOn()
	vrOff()
	left()
	right()
	up()
	down()
	home()
	presetOne()
	presetTwo()
	presetThree()
	presetCommand(1)
	start()
	stop()
	vidOn()
	vidOff()
}

def parse(String description) {
	//log.warn "parse() '$description'"
	if(description &&  description != "updated") {
		try {
			def map = [:]
			def retResult = []
			def descMap = parseDescriptionAsMap(description)
			def msg = parseLanMessage(description)
			if(msg.status != 200) {			// the 5030L somtimes returns null status, but command worked
				log.warn "DESCRIPTION: ${description} DESCMAP: ${descMap} MSG: ${msg} MSG.STATUS ${msg.status}  MSG.DATA ${msg.data}"
			}
			//log.error "msg ${msg}"
			//log.error "msg.status ${msg.status}         msg.data ${msg.data}"
	
			//Image
			//if(descMap["bucket"] && descMap["key"]) {
			if(descMap["tempImageKey"] ) {
				try {
					storeTemporaryImage(descMap.tempImageKey, getPictureName())
				} catch(Exception e) {
					log.error e
				}
			} else if(descMap["headers"] && descMap["body"]) {
				def body = new String(descMap["body"].decodeBase64())
				log.trace "Body: ${body}"
			}
	
		 	if(msg.body) {
	
				//log.trace "msg.body: ${msg.body}"

				//log.debug "Video Recording Enabled: ${msg.body.contains("<record>\n<enable>1</enable>")}"
				//log.debug "Video Recording Disabled: ${msg.body.contains("<record>\n<enable>0</enable>")}"
				//log.debug "Motion Enabled: ${msg.body.contains("MotionDetectionEnable=1")}"
				//log.debug "Motion Disabled: ${msg.body.contains("MotionDetectionEnable=0")}"
		
	
				if (msg.body.contains("MotionDetectionEnable=1")) {
					log.debug "Motion is on"
					sendEvent(name: "switch", value: "on")
				}
				else if (msg.body.contains("MotionDetectionEnable=0")) {
					log.debug "Motion is off"
					sendEvent(name: "switch", value: "off")
				}
	
				if(msg.body.contains("MotionDetectionSensitivity=")) {
					//log.debug msg.body
					String[] lines = msg.body.split( '\n' )
					//log.debug lines[2]
					String[] sensitivity = lines[2].split( '=' )
					//log.debug sensitivity[1]
					int[] senseValue = sensitivity[1].toInteger()
					//log.debug senseValue
	
					sendEvent(name: "level",  value: "${senseValue[0]}")
					//sendEvent(name: "switch.setLevel", value: "${senseValue}")
				}
	
				if (msg.body.contains("pir=yes")) {
					log.debug "PIR is on"
					sendEvent(name: "switch2", value: "on");
				}
				else if (msg.body.contains("pir=no")) {
					log.debug "PIR is off"
					sendEvent(name: "switch2", value: "off");
				}

				if (msg.body.contains( "DayNightMode=3")) {
					log.debug "Night Vision is on"
					sendEvent(name: "switch3", value: "on")
					sendEvent(name: "switch3level", value: "inactive")
				}
				else if (msg.body.contains("DayNightMode=2")) {
					log.debug "Night Vision is off"
					sendEvent(name: "switch3", value: "off")
					sendEvent(name: "switch3level", value: "inactive")
				}
				else if (msg.body.contains("DayNightMode=1")) {
					log.debug "Night Vision is manual"
					sendEvent(name: "switch3", value: "manual")
					sendEvent(name: "switch3level", value: "inactive")
				}
				else if (msg.body.contains("DayNightMode=0")) {
					log.debug "Night Vision is auto"
					sendEvent(name: "switch3", value: "auto")

					if (msg.body.contains( "LightSensorControl=3")) {
						log.debug "Light Sensor Control is 3"
						sendEvent(name: "switch3level", value: 3)
					}
					else if (msg.body.contains("LightSensorControl=2")) {
						log.debug "Light Sensor Control is 2"
						sendEvent(name: "switch3level", value: 2)
					}
					else if (msg.body.contains("LightSensorControl=1")) {
						log.debug "Light Sensor Control is 1"
						sendEvent(name: "switch3level", value: 1)
					}
				}
	
				if(msg.body.contains("<record><enable>0</enable>")) {
					log.debug "Video Recording Disabled"
					sendEvent(name: "switch4", value: "off")
				}
				else if(msg.body.contains("<record><enable>1</enable>")) {
					log.debug "Video Recording Enabled"
					sendEvent(name: "switch4", value: "on")
				}
		
				if(msg.body?.contains("<code>ok</code>") && !msg.body.contains("<record><enable>0</enable>") && !msg.body.contains("<record><enable>1</enable>")) {
					log.debug "Camera has moved."
					sendEvent(name: "switch6", value: "down")
					sendEvent(name: "switch6", value: "up")
					sendEvent(name: "switch6", value: "left")
					sendEvent(name: "switch6", value: "right")
					sendEvent(name: "switch6", value: "presetOne")
					sendEvent(name: "switch6", value: "presetTwo")
					sendEvent(name: "switch6", value: "presetThree")
					sendEvent(name: "switch6", value: "home")
				 }
			}
		} catch (ex) { //needed to catch java.lang.ArrayIndexOutOfBoundsException when camera doesn't reply with anthing in the body
			//log.error "error", ex
		}
	} else {
		device.deviceNetworkId = "ID_WILL_BE_CHANGED_AT_RUNTIME_" + (Math.abs(new Random().nextInt()) % 99999 + 1)
	}
}

// handle commands
def take() {
	log.trace "take()"

	def path = "/image/jpeg.cgi"
	def method = "GET"

	doCmd(method, path, true)
}

def motionCmd(int motion, String attr)
{
	log.trace "motionCmd ($motion $attr)"

	//def path = "/config/motion.cgi?${attr}=yes"
	def path = "/motion.cgi?MotionDetectionEnable=${motion}&ConfigReboot=no"

	if(attr == "pir") {
		log.error "no PIR on this camera"
		return
	}
	/* IRLed
		path = "/nightmodecontrol.cgi?IRLed=${motion}&ConfigReboot=no"
		//path = "/daynight.cgi?IRLed=${motion}&ConfigReboot=no"
		//path = "/setControlDayNight.cgi?IRLed=${motion}&ConfigReboot=no"
	*/

	def method = "GET"
	doCmd(method, path)
}

def sensitivityCmd(int percent) {
	log.trace "sensitivityCmd: $percent"

	//def path = "/config/motion.cgi?sensitivity=${percent}&pir_sensitivity=${pir_percent}"
	def path = "/motion.cgi?MotionDetectionSensitivity=${percent}&ConfigReboot=No"

	def method = "GET"

	doCmd(method, path)
}

def nightCmd(String attr, String aattr) {
	log.trace "nightCmd: $attr $aattr"


	//def path = "/config/icr.cgi?mode=${aattr}"
	def path = "/daynight.cgi?DayNightMode=${attr}&ConfigReboot=no"
	def method = "GET"

	doCmd(method, path)
}

def videoCmd(int attr) {
	log.trace "videoCmd: $attr"

	def path = "/cgi/admin/recorder.cgi?recordEnable=${attr}&continuous=${attr}"
	def method = "GET"

	doCmd(method, path)
}

def audioCmd(int attr) {
	log.trace "audioCmd: $attr"

	def path = "/setControlAudio.cgi?AudioMute=${attr}"
	def method = "GET"

	doCmd(method, path)
}

def parseDescriptionAsMap(description) {
	description.split(",").inject([:]) { map, param ->
		def nameAndValue = param.split(":")
		map += [(nameAndValue[0].trim()):nameAndValue[1].trim()]
	}
}

private getPictureName() {
	def pictureUuid = java.util.UUID.randomUUID().toString().replaceAll('-', '')
	log.debug "getPictureName: pictureUuid $pictureUuid"
	def picName = device.deviceNetworkId.replaceAll(':', '') + "_$pictureUuid" + ".jpg"
	return picName
}

private String convertIPtoHex(ipAddress) {
	String hex = ipAddress.tokenize( '.' ).collect { String.format( '%02x', it.toInteger() ) }.join()
	//log.debug "IP address entered is $ipAddress and the converted hex code is $hex"
	return hex
}

private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04x', port.toInteger() )
	//log.debug "convertPortToHex: hexport $hexport"
	return hexport
}

private Integer convertHexToInt(hex) {
	Integer.parseInt(hex,16)
}


private String convertHexToIP(hex) {
	//log.trace "Convert hex to ip: $hex"
	[convertHexToInt(hex[0..1]),convertHexToInt(hex[2..3]),convertHexToInt(hex[4..5]),convertHexToInt(hex[6..7])].join(".")
}

private getHostAddress() {
	def parts = device.deviceNetworkId.split(":")
	log.debug "getHostAddress dni ${device.deviceNetworkId}"
	def ip = convertHexToIP(parts[0])
	def port = convertHexToInt(parts[1])
	return ip + ":" + port
}

def audioOn() {
	audioCmd(0)
}

def audioOff() {
	audioCmd(1)
}

def on() {
	log.debug "on: Enabling motion detection"
	return motionCmd(1, "enable")
}

def off() {
	log.debug "Off: Disabling motion detection"
	return motionCmd(0, "enable")
}

def pirOn() {
	log.debug "pirOn: Enabling PIR Sensor"
	return motionCmd(1, "pir")

}

def pirOff() {
	log.debug "pirOff: Disabling PIR Sensor"
	return motionCmd(0, "pir")
}

def setLevel(percent) {
	log.debug "Executing 'setLevel'"
	return sensitivityCmd(percent)	
}

def nvOn() {
	log.debug "nvOn: Always  Night Mode"
	//return nightCmd("night")
	return nightCmd("3", "night")
}

def nvOff() {
	log.debug "nvOff: Always Day Mode"
	//return nightCmd("day")
	return nightCmd("2", "day")
}

def nvManual() {
	log.debug "nvManual: Manual Day/Night Mode"
	//return nightCmd("day")
	return nightCmd("1", "manual")
}

def nvAuto() {
	log.debug "nvAuto: Automatic Day/Night"
	//return nightCmd("auto")
	return nightCmd("0", "auto")
}

def vrOn() {
	log.debug "vrOn: Video Recording On"
	return videoCmd(1)
}

def vrOff() {
	log.debug "vrOff: Video Recording Off"
	return videoCmd(0)
}

def doCmd(method, path, opt = null) {
	log.debug "doCmd( $method, $path)"
	def userpassascii = "${state.cameraUser}:${state.cameraPassword}"
	def userpass = "Basic " + userpassascii.encodeAsBase64().toString()
	def host = state.cameraIP
	def hosthex = convertIPtoHex(host).toUpperCase()
	def porthex = convertPortToHex(state.cameraPort).toUpperCase()
	device.deviceNetworkId = "$hosthex:$porthex"

	//log.debug "The device id configured is: $device.deviceNetworkId"

	def headers = [:]
	headers.put("HOST", "$host:$state.cameraPort")
	headers.put("Authorization", userpass)

	//log.debug "The Header is $headers"

	try {
		def hubAction = new physicalgraph.device.HubAction(
			method: method,
			path: path,
			url: "/myUrl",
			headers: headers
			)

		if(opt) { hubAction.options = [outputMsgToS3:true] }
		//log.debug "doCmd: hubAction is $hubAction"
		return hubAction
	}
	catch (Exception e) {
		log.error "Hit Exception $e on $hubAction", e
	}
}

def moveCmd(int move, int moveX, int moveY) {
	log.trace "moveCmd()"

	//def path = "/cgi/ptdc.cgi?command=set_relative_pos&posX=${moveX}&posY=${moveY}"
	def path = "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=${move}"

	def method = "GET"

	doCmd(method,path)
}

def presetCmd(preset) {
	log.trace "presetCmd()"

	//def presetIndex = (preset.toInteger() - 1)
	//def path = "/cgi/ptdc.cgi?command=goto_preset_position&index=${presetIndex}"
	def path = "/pantiltcontrol.cgi?PanTiltPresetPositionMove=${preset}"

	def method = "GET"

	doCmd(method,path)
}

def up() {
	log.trace "up: Moving Up"
	//moveCmd(0,5)
	moveCmd(1, 0, 5)
}

def left() {
	log.trace "left: Moving Left"
	//moveCmd(-5,0)
	moveCmd(3,-5,0)
}

def right() {
	log.trace "right: Moving Right"
	//moveCmd(5,0)
	moveCmd(5,5,0)
}

def down() {
	log.trace "down: Moving Down"
	//moveCmd(0,-5)
	moveCmd(7,0,-5)
}

def home() {
	log.trace "home: Moving to Home position"
	//def path = "/cgi/ptdc.cgi?command=go_home"
	def move = 4 as Integer
	def path = "/pantiltcontrol.cgi?PanSingleMoveDegree=5&TiltSingleMoveDegree=5&PanTiltSingleMove=4"
	def method = "GET"
	doCmd(method,path)

}

def presetOne() {
	log.trace "presetOne: Moving to Preset position"
	presetCmd(state.presetOne.toInteger())
}

def presetTwo() {
	log.trace "presetTwo: Moving to Preset position"
	presetCmd(state.presetTwo.toInteger())
}

def presetThree() {
	log.trace "presetThree: Moving to Preset position"
	presetCmd(state.presetThree.toInteger())
}

def presetCommand(position) {
	log.trace "presetCommand: Moving to Preset position: ${position}"
	presetCmd(position.toInteger())
}

def start() {
	log.trace "start()"
	def dataLiveVideo = [
		//OutHomeURL	: "rtsp://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/live1.sdp",
		//InHomeURL	: "rtsp://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/live1.sdp",
		OutHomeURL	: "http://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/mjpeg.cgi?channel=1.mjpeg",
		InHomeURL	: "http://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/mjpeg.cgi?channel=1.mjpeg",
		ThumbnailURL	: "http://cdn.device-icons.smartthings.com/camera/dlink-indoor@2x.png",
		cookie		: [key: "key", value: "value"]
	]

	def event = [
		name			: "stream",
		value			: groovy.json.JsonOutput.toJson(dataLiveVideo).toString(),
		data			: groovy.json.JsonOutput.toJson(dataLiveVideo),
		descriptionText: "Starting the livestream",
		eventType		: "VIDEO",
		displayed		: false,
		isStateChange		: true
	]
	sendEvent(event)
}

def stop() {
	log.trace "stop()"
}

def vidOn() {
	log.trace "vidon() does nothing"
	// no-op
}

def vidOff() {
	log.trace "vidoff() does nothing"
	// no-op
}

def installed(){
	configure()
}

def updated(){
	configure()
}

def configure(){
	sendEvent(name:"switch5", value:"on")

	//log.debug "configure: parent cameraIP ${parent.state.cameraIP} $parent"
	state.cameraIP = parent.state.CameraIP
	state.cameraPort = parent.state.CameraPort
	state.videoIP = parent.state.VideoIP
	state.videoPort = parent.state.VideoPort
	state.cameraUser = parent.state.CameraUser
	state.cameraPassword = parent.state.CameraPassword
	state.presetOne = parent.state.PresetOne
	state.presetTwo = parent.state.PresetTwo
	state.presetThree = parent.state.PresetThree
}

def getInHomeURL() {
	 //[InHomeURL: "rtsp://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/live1.sdp"]
	 [InHomeURL: "http://${state.cameraUser}:${state.cameraPassword}@${state.videoIP}:${state.videoPort}/mjpeg.cgi?channel=1.mjpeg"]
}
