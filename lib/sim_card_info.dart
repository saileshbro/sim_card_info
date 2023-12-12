
import 'sim_card_info_platform_interface.dart';

class SimCardInfo {
  Future<String?> getSimInfo() {
    return SimCardInfoPlatform.instance.getSimInfo();
  }
}
