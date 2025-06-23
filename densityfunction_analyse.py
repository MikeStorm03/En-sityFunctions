import numpy as np
import matplotlib.pyplot as plt
from matplotlib.colors import LinearSegmentedColormap

width, height = 200, 200
map_range = [-5000,5000]
scale = 0.1

def clamp(value: float, min_value: float, max_value: float) -> float:
    return min_value if value < min_value else min(value, max_value)

def fake_noise(x, z):
    return np.sin(x * 0.1) * np.cos(z * 0.1)

def get_height_value(x, z):
    i = int(x) // 2
    j = int(z) // 2
    k = int(x) % 2
    l = int(z) % 2
    f = -100

    for m in range(-12, 13):
        for n in range(-12, 13):
            o = i + m
            p = j + n
            if fake_noise(o, p) < -0.8999999761581421:
                g = (abs(float(o)) * 3439.0 + abs(float(p)) * 147.0) % 13.0 + 9.0
                h = float(k - m * 2)
                q = float(l - n * 2)
                r = 100.0 - np.sqrt(h * h + q * q) * g
                r = clamp(r, -100.0, 80.0)
                f = max(f, r)
    return f

x_range = np.linspace(map_range[0], map_range[1], width)
z_range = np.linspace(map_range[0], map_range[1], height)

height_map = np.zeros((height, width))
for zi, z in enumerate(z_range):
    for xi, x in enumerate(x_range):
        height_map[zi, xi] = get_height_value(x * scale, z * scale)

min_val = height_map.min()
max_val = height_map.max()
normalized = (height_map - min_val) / (max_val - min_val)

cmap = LinearSegmentedColormap.from_list("custom_map", ["#420050", "#FFEB0A"])

# Hiển thị bản đồ
plt.figure(figsize=(8, 8))
plt.imshow(normalized, cmap=cmap, extent=(map_range[0], map_range[1], map_range[0], map_range[1]))
plt.axis('off')
plt.title("2D Height Map", fontsize=14)
plt.tight_layout()
plt.show()
