#ifndef FLUTTER_PLUGIN_SIM_CARD_INFO_PLUGIN_H_
#define FLUTTER_PLUGIN_SIM_CARD_INFO_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace sim_card_info {

class SimCardInfoPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  SimCardInfoPlugin();

  virtual ~SimCardInfoPlugin();

  // Disallow copy and assign.
  SimCardInfoPlugin(const SimCardInfoPlugin&) = delete;
  SimCardInfoPlugin& operator=(const SimCardInfoPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace sim_card_info

#endif  // FLUTTER_PLUGIN_SIM_CARD_INFO_PLUGIN_H_
