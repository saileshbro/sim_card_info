# sim_card_info

A Flutter plugin to get SIM card information.

## Features

- Fetches SIM card information from the device.
- Handles exceptions and returns appropriate messages.

## Getting Started

### Installation

Add `sim_card_info` to your `pubspec.yaml` file:

```yaml
dependencies:
  sim_card_info: ^0.0.1

```

then run `flutter pub get` in your terminal.

## Usage

### Import

```dart
import 'package:sim_card_info/sim_card_info.dart';
```

### Get SIM card information

```dart
SimCardInfo simCardInfo = await SimCardInfo.getSimCardInfo;
```

## Permissions
refer to `example` directory for a complete sample app using`sim_card_info` plugin.

## Issues and feedback
For any issue or feedback please [create an issue](https://github.com/FadyFouad/sim_card_info/issues/new).


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.