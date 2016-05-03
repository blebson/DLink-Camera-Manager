/**
*  Dlink PTZ Video Camera Child
*
*  Copyright 2016 Ben Lebson
*  Parent/Child SmartApp based on Patrick Stuart's Generic Video Camera SmartApp
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions and limitations under the License.
*
*/
definition(
    name: "Dlink PTZ Video Camera Child",
    namespace: "blebson",
    author: "Ben Lebson",
    description: "Dlink Child PTZ Video Camera SmartApp",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Camera/dlink.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Camera/dlink@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Camera/dlink@3x.png")

preferences {
    page(name: "mainPage", title: "Install Video Camera", install: true, uninstall:true) {
        section("Camera Name") {
            label(name: "label", title: "Name This Camera", required: true, multiple: false, submitOnChange: true)
        }
        section("Add a Camera") {
        	input("CameraType","enum", title: "Camera Model", description: "Please select your camera model", required:false, submitOnChange: true,
            options: ["DCS-5009L", "DCS-5010L", "DCS-5020L", "DCS-5029L", "DCS-5211L", "DCS-5222L"], displayDuringSetup: true)
            }
        section("Camera Settings:"){
        	input("CameraIP", "string", title:"Camera IP Address", description: "Please enter your camera's IP Address", required: true, displayDuringSetup: true)
    		input("CameraPort", "string", title:"Camera Port", description: "Please enter your camera's HTTP Port", defaultValue: 80 , required: true, displayDuringSetup: true)
    		input("VideoIP", "string", title:"Video IP Address", description: "Please enter your camera's IP Address (use external IP if you are using port forwarding)", required: true, displayDuringSetup: true)
    		input("VideoPort", "string", title:"Video Port", description: "Please enter your camera's Video Port (use external Port if you are using port forwarding)", required: true, displayDuringSetup: true)
    		input("CameraUser", "string", title:"Camera User", description: "Please enter your camera's username", required: false, displayDuringSetup: true)
    		input("CameraPassword", "password", title:"Camera Password", description: "Please enter your camera's password", required: false, displayDuringSetup: true)
            input("CameraPresetOne", "string", title:"Camera Preset 1", description: "Please enter which preset view you want to use (other than 'Home')", defaultValue: 1 , required: true, displayDuringSetup: true)
			input("CameraPresetTwo", "string", title:"Camera Preset 2", description: "Please enter which preset view you want to use (other than 'Home')", defaultValue: 2 , required: false, displayDuringSetup: true)
    		input("CameraPresetThree", "string", title:"Camera Preset 3", description: "Please enter which preset view you want to use (other than 'Home')", defaultValue: 3 , required: false, displayDuringSetup: true)
            }
        section("Hub Settings"){
        	input("hubName", "hub", title:"Hub", description: "Please select your Hub", required: true, displayDuringSetup: true)
        }
    }
    
}

def installed() {
    log.debug "Installed"

    initialize()
}

def updated() {
    log.debug "Updated"

    unsubscribe()
    initialize()
}

def initialize() {

        state.CameraIP = CameraIP
        state.CameraPort = CameraPort
        state.VideoIP = VideoIP
        state.VideoPort = VideoPort
        state.CameraUser = CameraUser
        state.CameraPassword = CameraPassword
        state.PresetOne = CameraPresetOne
        state.PresetTwo = CameraPresetTwo
        state.PresetThree = CameraPresetThree
        
        
        log.debug "Camera IP: ${state.CameraIP}"
        log.debug "Camera Port: ${state.CameraPort}"
        log.debug "Video IP: ${state.VideoIP}"
        log.debug "Video Port: ${state.VideoPort}"
        log.debug "Camera User: ${state.CameraUser}"
        log.debug "Camera Password: ${state.CameraPassword}"
        
        
	try {
        def DNI = (Math.abs(new Random().nextInt()) % 99999 + 1).toString()        
        def cameras = getChildDevices()
        if (cameras) {
            cameras[0].configure()
        }
        else {        
        	def childDevice = addChildDevice("blebson", CameraType, DNI, hubName.id, [name: app.label, label: app.label, completedSetup: true])
        }
    } catch (e) {
    	log.error "Error creating device: ${e}"
    }
}

private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}