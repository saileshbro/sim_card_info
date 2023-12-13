import 'package:permission_handler/permission_handler.dart';
import 'package:sim_card_info/sim_info.dart';

import 'sim_card_info_platform_interface.dart';

/// An implementation of [SimCardInfoPlatform] that uses method channels.
class SimCardInfo {
  Future<List<SimInfo>?> getSimInfo() async {
    /// request phone permission first
    await Permission.phone.request();
    return SimCardInfoPlatform.instance.getSimInfo();
  }
}
