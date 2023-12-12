#include "include/sim_card_info/sim_card_info_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "sim_card_info_plugin.h"

void SimCardInfoPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  sim_card_info::SimCardInfoPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
